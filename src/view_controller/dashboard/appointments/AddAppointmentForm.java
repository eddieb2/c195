package view_controller.dashboard.appointments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.Helper;
import view_controller.dashboard.DashboardController;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class AddAppointmentForm implements Initializable {
    @FXML private Button submitButton;
    @FXML private Button cancelButton;
    @FXML private TextField appointmentIdField;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField locationField;
    @FXML private TextField typeField;
    @FXML private ComboBox<Time> startTimeField;
    @FXML private ComboBox<Time> endTimeField;
    @FXML private ComboBox<Integer> customerIdComboBox;
    @FXML private ComboBox<Integer> userIdComboBox;
    @FXML private ComboBox<Integer> contactIdComboBox;
    @FXML private DatePicker startDateField;
    @FXML private DatePicker endDateField;

    // TODO: 11/16/2022 Populate start/end time field with available times
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdComboBox.setItems(DashboardController.customerIds);
        userIdComboBox.setItems(DashboardController.userIds);
        contactIdComboBox.setItems(DashboardController.contactIds);
    }


    public void submitForm(ActionEvent event) {

    }

    public void closeForm(ActionEvent event) {
        Helper.closeWindow(cancelButton);
    }

}
