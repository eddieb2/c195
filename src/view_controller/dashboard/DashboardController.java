package view_controller.dashboard;

import DAO.CustomerQueries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.model.Customer;
import view_controller.dashboard.appointments.AppointmentsController;
import view_controller.dashboard.customers.CustomersTabController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

// YT video explaining how to link 2 fxml files into one
// https://www.youtube.com/watch?v=osIRfgHTfyg

public class DashboardController implements Initializable {
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
    @FXML private CustomersTabController customersController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

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
    @FXML private void deleteCustomer() throws SQLException, IOException {
        Customer selectedCustomer = CustomersTabController.selectedCustomer;
        Alert deletionConfirmation = new Alert(Alert.AlertType.CONFIRMATION);

        try {
            // Prompt for confirmation
            deletionConfirmation.setContentText("Confirm selection.");
            Optional<ButtonType> result = deletionConfirmation.showAndWait();

            // Delete selected customer
            if (result.get() == ButtonType.OK) {
                CustomerQueries.deleteCustomer(selectedCustomer);

                // Refresh customer table
                ObservableList<Customer> newCustomerList = CustomerQueries.getAllCustomers();
                CustomersTabController.customers.setAll(newCustomerList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
