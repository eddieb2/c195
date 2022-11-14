package view_controller.dashboard.appointments;

import DAO.AppointmentQueries;
import src.model.Appointment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {


    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment,String> idCol;
    @FXML private TableColumn<Appointment,Integer> titleCol;
    @FXML private TableColumn<Appointment,String> descriptionCol;
    @FXML private TableColumn<Appointment,String> locationCol;
    @FXML private TableColumn<Appointment,String> typeCol;
    @FXML private TableColumn<Appointment,Date> startCol;
    @FXML private TableColumn<Appointment,Date> endCol;
    @FXML private TableColumn<Appointment,Integer> customerIdCol;
    @FXML private TableColumn<Appointment,Integer> userIdCol;
    @FXML private TableColumn<Appointment,Integer> contactIdCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        // Queries the database and saves all the appointments to an observable list.
        ObservableList<Appointment> appointments = null;

        try {
            appointments = AppointmentQueries.getAllAppointments();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println(throwable);
        }

        appointmentsTable.setItems(appointments);
    }
}
