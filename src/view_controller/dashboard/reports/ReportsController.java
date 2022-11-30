package view_controller.dashboard.reports;

import DAO.AppointmentQueries;
import DAO.CustomerQueries;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import src.model.*;
import utils.Helper;
import view_controller.dashboard.DashboardController;
import view_controller.dashboard.appointments.AppointmentsTabController;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Displays reports.
 */
public class ReportsController implements Initializable {

    @FXML private Button closeButton;
    @FXML private ComboBox<Contact> contactComboBox;
    @FXML private TableView<Appointment> contactScheduleTable;
    @FXML private TableColumn<Appointment, Integer> appointmentIdColumn;
    @FXML private TableColumn<Appointment, String> titleColumn;
    @FXML private TableColumn<Appointment, String> typeColumn;
    @FXML private TableColumn<Appointment, String> descriptionColumn;
    @FXML private TableColumn<Appointment, LocalDateTime> startDateColumn;
    @FXML private TableColumn<Appointment, LocalDateTime> endDateColumn;
    @FXML private TableColumn<Appointment, LocalDateTime> startTimeColumn;
    @FXML private TableColumn<Appointment, LocalDateTime> endTimeColumn;
    @FXML private TableColumn<Appointment, Integer> customerIdColumn;
    @FXML private TableView<ReportByType> appointmentTotalsTable;
    @FXML private TableColumn<ReportByType, String> appointmentTypeColumn;
    @FXML private TableColumn<ReportByType, Integer> appointmentTotalColumn;
    @FXML private TableColumn<ReportByType, String> appointmentMonthColumn;
    @FXML private TableView<ReportByCountry> customersByCountryTable;
    @FXML private TableColumn<ReportByCountry, String> countryColumn;
    @FXML private TableColumn<ReportByCountry, Integer> totalCustomersColumn;


    /**
     * Contains a lambda expression -- used to filter appointments based on the contactComboBox selection.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(DashboardController.contacts);

        // contactScheduleTable
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        Helper.cellFormatterToTime(startTimeColumn);
        Helper.cellFormatterToTime(endTimeColumn);
        Helper.cellFormatterToDate(startDateColumn);
        Helper.cellFormatterToDate(endDateColumn);

        // Filters the contactScheduleTable based on the selection in the contactComboBox
        contactComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, part, t1) -> {
            Contact selectedContact = contactComboBox.getSelectionModel().getSelectedItem();

            // Lambda expression used to filter the appointment list.
            FilteredList<Appointment> filteredAppointments = AppointmentsTabController.appointments.filtered(appointment -> appointment.getContactId().equals(selectedContact.getContactId()));
            contactScheduleTable.setItems(filteredAppointments);
        });

        // customersByCountryTable
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        totalCustomersColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        // appointmentTotalsTable
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        appointmentMonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));

        try {
            customersByCountryTable.setItems(CustomerQueries.getCustomersTotalByCountry());
            appointmentTotalsTable.setItems(AppointmentQueries.getAppointmentTotalsByMonth());
            contactScheduleTable.setItems(AppointmentsTabController.appointments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes window when the close button is pressed.
     * @param event
     */
    public void closeWindow(ActionEvent event) {
        Helper.closeWindow(closeButton);
    }
}
