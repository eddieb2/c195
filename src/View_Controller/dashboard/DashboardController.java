package view_controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view_controller.dashboard.appointments.AppointmentsController;
import view_controller.dashboard.customers.CustomersController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

// YT video explaining how to link 2 fxml files into one
// https://www.youtube.com/watch?v=osIRfgHTfyg

public class DashboardController {
    @FXML private AnchorPane dashboard;
    @FXML private AnchorPane customersTab;
    @FXML private AnchorPane appointmentsTab;
    @FXML private ButtonBar customerButtonBar;
    @FXML private Button addCustomerButton;
    @FXML private Button updateCustomerButton;
    @FXML private Button deleteCustomerButton;
    @FXML private Button addAptButton;
    @FXML private Button updateAptButton;
    @FXML private Button deleteAptButton;


    // Not sure if these two variables are needed
    @FXML private AppointmentsController appointmentsController;
    @FXML private CustomersController customersController;

    /**
     * Opens the addCustomerForm when the addCustomerButton is fired.
     */
    // FIX ME: You can continuously open this window. Disable/enable after click/exit
    @FXML private void addCustomerForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customers/add_customer_form.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the updateCustomerForm when the updateCustomerButton is fired.
     */
    @FXML private void updateCustomerForm() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customers/update_customer_form.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Update Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Removes a customer from the UI and the database
     */
    private void deleteCustomer() {

    }

    /**
     * Opens the addAppointmentForm when the addAppointmentButton is fired.
     */
    private void addAppointmentForm() {

    }

    /**
     * Opens the updateAppointmentForm when the updateAppointmentButton is fired.
     */
    private void updateAppointmentForm() {

    }

    /**
     * Removes an appointment from the UI and the database
     */
    private void deleteAppointment() {

    }
}
