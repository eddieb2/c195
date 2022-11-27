package view_controller.dashboard;

import DAO.AppointmentQueries;
import DAO.ContactQueries;
import DAO.CustomerQueries;
import DAO.UserQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import src.model.Appointment;
import src.model.Contact;
import src.model.Customer;
import src.model.User;
import view_controller.dashboard.appointments.AppointmentsTabController;
import view_controller.dashboard.customers.CustomersTabController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

// YT video explaining how to link 2 fxml files into one
// https://www.youtube.com/watch?v=osIRfgHTfyg

public class DashboardController implements Initializable {
    // TODO: 11/16/2022 Not sure if these two variables are needed
    @FXML private AppointmentsTabController appointmentsTabController;
    @FXML private CustomersTabController customersTabController;

    public static ObservableList<User> users = FXCollections.observableArrayList();
    public static ObservableList<Integer> userIds = FXCollections.observableArrayList();
    public static ObservableList<Contact> contacts = FXCollections.observableArrayList();
    public static ObservableList<Integer> contactIds = FXCollections.observableArrayList();
    public static ObservableList<Customer> customers = FXCollections.observableArrayList();
    public static ObservableList<Integer> customerIds = FXCollections.observableArrayList();

    // FIXME: 11/16/2022 Cant move this variable here from the customer tab controller. The customers table wont populate.
//    public static ObservableList<Customer> customers = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Queries the database and saves all the appointments/users/contacts to an observable list.
        // Populates the appointments table view with appointments-
        try {
            // FIXME: 11/16/2022 Cant move this variable here from the customer tab controller. The customers table wont populate.
            customers = CustomerQueries.getAllCustomers();
            customerIds = CustomerQueries.getAllCustomerIds();
            users = UserQueries.getAllUsers();
            userIds = UserQueries.getAllUserIds();
            contacts = ContactQueries.getAllContacts();
            contactIds = ContactQueries.getAllContactIds();

            checkUpcomingAppointments();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    /**
     * Opens the addCustomerForm when the addCustomerButton is fired.
     */
    // FIXME: 11/16/2022 When the dashboard window closes, any form windows remain open.
    // FIXME: 11/16/2022 : You can continuously open this window. Disable/enable after click/exit
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
    // FIXME: 11/16/2022 : You can continuously open this window. Disable/enable after click/exit
    @FXML private void updateCustomerForm() throws IOException {
        Customer selectedCustomer = CustomersTabController.selectedCustomer;
        Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
        selectionAlert.setContentText("No customer selected.");

        if (selectedCustomer == null) {
            selectionAlert.show();
            return;
        }

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
        deletionConfirmation.setContentText("Confirm selection.");
        Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
        selectionAlert.setContentText("No customer selected.");

        if (selectedCustomer == null) {
            selectionAlert.show();
            return;
        }

        try {
            // Prompt for confirmation
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
    // FIXME: 11/16/2022 : You can continuously open this window. Disable/enable after click/exit
    @FXML private void addAppointmentForm() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("appointments/add_appointment_form.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the updateAppointmentForm when the updateAppointmentButton is fired.
     */
    // FIXME: 11/16/2022 : You can continuously open this window. Disable/enable after click/exit
    @FXML private void updateAppointmentForm() throws IOException {
        Appointment selectedAppointment = AppointmentsTabController.selectedAppointment;
        Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
        selectionAlert.setContentText("No appointment selected.");

        if (selectedAppointment == null) {
            selectionAlert.show();
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("appointments/update_appointment_form.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Update Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Removes an appointment from the UI and the database
     */
    @FXML private void deleteAppointment() {
        Appointment selectedAppointment = AppointmentsTabController.selectedAppointment;
        Alert deletionConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        deletionConfirmation.setContentText("Confirm selection.");
        Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
        selectionAlert.setContentText("No appointment selected.");
        Alert deletedAppointmentInfoAlert = new Alert(Alert.AlertType.INFORMATION);
        deletedAppointmentInfoAlert.setContentText("Appointment Canceled. \nAppointment ID: " + selectedAppointment.getAppointmentId() + "  Type: " + selectedAppointment.getType());

        if (selectedAppointment == null) {
            selectionAlert.show();
            return;
        }

        try {
            // Prompt for confirmation
            Optional<ButtonType> result = deletionConfirmation.showAndWait();

            // Delete selected appointment
            if (result.get() == ButtonType.OK) {
                AppointmentQueries.deleteAppointmentById(selectedAppointment);

                // Refresh customer table -> communicates with the AppointmentsTabController
                ObservableList<Appointment> newAppointmentList = AppointmentQueries.getAllAppointments();
                AppointmentsTabController.appointments.setAll(newAppointmentList);

                deletedAppointmentInfoAlert.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks all appointments to see if there are any scheduled within the next 15 minutes of the current time.
     */
    public static void checkUpcomingAppointments() {
        Alert aptAlert = new Alert(Alert.AlertType.INFORMATION);

        Integer appointmentId = null;
        LocalTime appointmentTime = null;
        LocalDate appointmentDate = null;

        for (Appointment apt: AppointmentsTabController.appointments) {
            LocalDateTime localDateTimeNow = LocalDateTime.now();
            LocalDateTime aptDateTimeStart = apt.getStart();
            LocalDateTime timeBeforeApt = aptDateTimeStart.minusMinutes(15).plusSeconds(1);

            if (localDateTimeNow.isBefore(aptDateTimeStart) && localDateTimeNow.isAfter(timeBeforeApt)) {
                appointmentId = apt.getAppointmentId();
                appointmentDate = apt.getStart().toLocalDate();
                appointmentTime = apt.getStart().toLocalTime();
            }

        }

        if (appointmentId == null){
            aptAlert.setContentText("There are no upcoming appointments.");
        } else {
            aptAlert.setContentText("There is an appointment scheduled within the next 15 minutes! " +
                    "\n\nAppointment ID: " + appointmentId + " \nDate: " + appointmentDate + " \nTime: " + appointmentTime);
        }

        aptAlert.show();
    }
}
