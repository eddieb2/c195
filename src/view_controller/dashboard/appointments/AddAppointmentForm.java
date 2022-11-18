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

    // TODO: 11/16/2022 Populate start/end time field with available times
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentsTabController.populateComboBoxes(customerIdComboBox, userIdComboBox, contactIdComboBox, startTimeField, endTimeField);
    }


    public void submitForm(ActionEvent event) throws SQLException {
        // Alerts
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText("One or more fields are blank. All fields must be complete.");
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Appointment successfully created.");

        // Continuously Prompts user with an error message if any of the fields are empty
        if (titleField.getText().isEmpty() || descriptionField.getText().isEmpty() || locationField.getText().isEmpty() ||
                typeField.getText().isEmpty() || startTimeField.getSelectionModel().isEmpty() || endTimeField.getSelectionModel().isEmpty() ||
                startDatePicker.getValue() == null || endDatePicker.getValue() == null || customerIdComboBox.getSelectionModel().isEmpty() ||
                userIdComboBox.getSelectionModel().isEmpty() || contactIdComboBox.getSelectionModel().isEmpty()) {
            errorAlert.show();
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
        AppointmentQueries.addAppointment(newAppointment);

        // RELOAD MAIN DATA ON DASHBOARD AFTER SUBMISSION AND CLOSE WINDOW
        ObservableList<Appointment> newAppointmentList = AppointmentQueries.getAllAppointments();
        AppointmentsTabController.appointments.setAll(newAppointmentList);

        successAlert.show();
        Helper.closeWindow(submitButton);
    }

    public void closeForm(ActionEvent event) {
        Helper.closeWindow(cancelButton);
    }

}
