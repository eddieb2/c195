package view_controller.dashboard.appointments;

import DAO.AppointmentQueries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import src.model.Appointment;
import src.model.Contact;
import src.model.Customer;
import src.model.User;
import utils.Helper;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This form enables the user to add a new appointment.
 */
public class AddAppointmentForm implements Initializable {

    @FXML private Button submitButton;
    @FXML private Button cancelButton;
    @FXML private TextField appointmentIdField;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField locationField;
    @FXML private TextField typeField;
    @FXML private ComboBox<Customer> customerIdComboBox;
    @FXML private ComboBox<User> userIdComboBox;
    @FXML private ComboBox<Contact> contactIdComboBox;
    @FXML private ComboBox<LocalTime> startTimeField;
    @FXML private ComboBox<LocalTime> endTimeField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentsTabController.populateComboBoxes(customerIdComboBox, userIdComboBox, contactIdComboBox, startTimeField, endTimeField);

        Helper.formatCalendar(startDatePicker); // disables past dates and disables weekends
        Helper.formatCalendar(endDatePicker); // disables past dates and disables weekends
    }


    /**
     * Adds a new appointment to the database.
     * @param event
     * @throws SQLException
     */
    public void submitForm(ActionEvent event) throws SQLException {
        // Alerts
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText("One or more fields are blank. All fields must be complete.");
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Appointment successfully created.");
        Alert timeDateError = new Alert(Alert.AlertType.ERROR);
        timeDateError.setContentText("Start date/time needs to be before end date/time.");
        Alert schedulingError = new Alert(Alert.AlertType.ERROR);
        Appointment overlappingAppointment = null;

        // Continuously Prompts user with an error message if any of the fields are empty
        if (titleField.getText().isEmpty() || descriptionField.getText().isEmpty() || locationField.getText().isEmpty() ||
                typeField.getText().isEmpty() || startTimeField.getSelectionModel().isEmpty() || endTimeField.getSelectionModel().isEmpty() ||
                startDatePicker.getValue() == null || endDatePicker.getValue() == null || customerIdComboBox.getSelectionModel().isEmpty() ||
                userIdComboBox.getSelectionModel().isEmpty() || contactIdComboBox.getSelectionModel().isEmpty()) {
            errorAlert.show();
            return;
        }

        // Dates and time guard clause
        if (endDatePicker.getValue().isBefore(startDatePicker.getValue()) || endTimeField.getValue().isBefore(startTimeField.getValue()) || endTimeField.getValue().equals(startTimeField.getValue())) {
            timeDateError.show();
            return;
        }

        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTimeField.getSelectionModel().getSelectedItem());
        LocalDateTime endDateTime = LocalDateTime.of(endDatePicker.getValue(), endTimeField.getSelectionModel().getSelectedItem());
        Integer customerId = customerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
        Integer userId = userIdComboBox.getSelectionModel().getSelectedItem().getUserId();
        Integer contactId = contactIdComboBox.getSelectionModel().getSelectedItem().getContactId();


        Appointment newAppointment = new Appointment(title, description, location, type, startDateTime, endDateTime, customerId, userId, contactId);

        // TODO: Move to a function
        // Check for overlapping appointments
        for (Appointment scheduledAppointment : AppointmentQueries.getAllAppointments()) {
            if (scheduledAppointment.getStart().isBefore(newAppointment.getEnd()) && newAppointment.getStart().isBefore(scheduledAppointment.getEnd())) {
                overlappingAppointment = scheduledAppointment; // used to display the overlapping apt
                schedulingError.setContentText("Appointment #" + overlappingAppointment.getAppointmentId() + " is scheduled between " + overlappingAppointment.getStart().toLocalTime() + " - " + overlappingAppointment.getEnd().toLocalTime() + ". Please pick another date and time slot.");
                schedulingError.show();

                return;
            }
        }

        AppointmentQueries.addAppointment(newAppointment);

        // TODO: add a function to reload all data across the entire application
        // RELOAD MAIN DATA ON DASHBOARD AFTER SUBMISSION AND CLOSE WINDOW
        ObservableList<Appointment> newAppointmentList = AppointmentQueries.getAllAppointments();
        AppointmentsTabController.appointments.setAll(newAppointmentList);

        successAlert.show();
        Helper.closeWindow(submitButton);
    }

    /**
     * Closes the current form.
     * @param event
     */
    public void closeForm(ActionEvent event) {
        Helper.closeWindow(cancelButton);
    }

}
