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
import view_controller.dashboard.DashboardController;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This form enables the user to update an existing appointment.
 */
public class UpdateAppointmentForm implements Initializable {

    @FXML private Button submitButton;
    @FXML private Button cancelButton;
    @FXML private TextField appointmentIdField;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField locationField;
    @FXML private TextField typeField;
    @FXML private ComboBox<LocalTime> startTimeField;
    @FXML private ComboBox<LocalTime> endTimeField;
    @FXML private ComboBox<Customer> customerIdComboBox;
    @FXML private ComboBox<User> userIdComboBox;
    @FXML private ComboBox<Contact> contactIdComboBox;
    @FXML private DatePicker endDatePicker;
    @FXML private DatePicker startDatePicker;

    private Appointment selectedAppointment = AppointmentsTabController.selectedAppointment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentsTabController.populateComboBoxes(customerIdComboBox, userIdComboBox, contactIdComboBox, startTimeField, endTimeField);
        Helper.formatCalendar(startDatePicker); // disables past dates and disables weekends
        Helper.formatCalendar(endDatePicker); // disables past dates and disables weekends

        try {
            loadAppointment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the selected appointment's information into the text boxes and combo boxes.
     */
    public void loadAppointment() {
        appointmentIdField.setText(selectedAppointment.getAppointmentId().toString());
        titleField.setText(selectedAppointment.getTitle());
        descriptionField.setText(selectedAppointment.getDescription());
        locationField.setText(selectedAppointment.getLocation());
        typeField.setText(selectedAppointment.getLocation());

        Customer appointmentCustomer = null;
        User appointmentUser = null;
        Contact appointmentContact = null;

        for (Customer customer: DashboardController.customers) {
            if (customer.getCustomerId().equals(selectedAppointment.getCustomerId())) {
                appointmentCustomer = customer;
            }
        }

        for (User user: DashboardController.users) {
            if (user.getUserId().equals(selectedAppointment.getUserId())){
                appointmentUser = user;
            }
        }

        for (Contact contact: DashboardController.contacts) {
            if (contact.getContactId().equals(selectedAppointment.getContactId())){
                appointmentContact = contact;
            }
        }

        customerIdComboBox.setValue(appointmentCustomer);
        userIdComboBox.setValue(appointmentUser);
        contactIdComboBox.setValue(appointmentContact);


        // Populates the start/end time combo boxes.
        LocalTime startTime = selectedAppointment.getStart().toLocalTime();
        LocalTime endTime = selectedAppointment.getEnd().toLocalTime();
        startTimeField.setValue(startTime);
        endTimeField.setValue(endTime);

        // Populates the start/end date date pickers.
        LocalDate startDate = selectedAppointment.getStart().toLocalDate();
        LocalDate endDate = selectedAppointment.getEnd().toLocalDate();
        startDatePicker.setValue(startDate);
        endDatePicker.setValue(endDate);
    }

    /**
     * Updates an existing appointment.
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

        // Updates appointment in the DB
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        LocalDateTime startDT = LocalDateTime.of(startDatePicker.getValue(), startTimeField.getValue());
        LocalDateTime endDT = LocalDateTime.of(endDatePicker.getValue(), endTimeField.getValue());
        Customer customer = customerIdComboBox.getValue();
        User user = userIdComboBox.getValue();
        Contact contact = contactIdComboBox.getValue();

        selectedAppointment.setTitle(title);
        selectedAppointment.setDescription(description);
        selectedAppointment.setLocation(location);
        selectedAppointment.setType(type);
        selectedAppointment.setStart(startDT);
        selectedAppointment.setEnd(endDT);
        selectedAppointment.setCustomerId(customer.getCustomerId());
        selectedAppointment.setUserId(user.getUserId());
        selectedAppointment.setContactId(contact.getContactId());

        // Check for overlapping appointments
        for (Appointment scheduledAppointment : AppointmentQueries.getAllAppointments()) {

            // This if-statement allows us to update the selected appointment. Otherwise, the program will check the selected appointment against itself.
            if (scheduledAppointment.getAppointmentId() != selectedAppointment.getAppointmentId()){

                if (scheduledAppointment.getStart().isBefore(selectedAppointment.getEnd()) && selectedAppointment.getStart().isBefore(scheduledAppointment.getEnd())) {
                    overlappingAppointment = scheduledAppointment; // used to display the overlapping apt
                    schedulingError.setContentText("Appointment #" + overlappingAppointment.getAppointmentId() + " is scheduled between " + overlappingAppointment.getStart().toLocalTime() + " - " + overlappingAppointment.getEnd().toLocalTime() + ". Please pick another date and time slot.");
                    schedulingError.show();

                    return;
                }

            }

        }

        // Updates the appointment
        AppointmentQueries.updateAppointment(selectedAppointment);

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
