package view_controller.dashboard.appointments;

import DAO.AppointmentQueries;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import src.model.Appointment;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AppointmentsTabController implements Initializable {
    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment,String> idCol;
    @FXML private TableColumn<Appointment,Integer> titleCol;
    @FXML private TableColumn<Appointment,String> descriptionCol;
    @FXML private TableColumn<Appointment,String> locationCol;
    @FXML private TableColumn<Appointment,String> typeCol;
    @FXML private TableColumn<Appointment,Timestamp> startCol;
    @FXML private TableColumn<Appointment,Timestamp> endCol;
    @FXML private TableColumn<Appointment, Timestamp> createDateCol;
    @FXML private TableColumn<Appointment, String> createdByCol;
    @FXML private TableColumn<Appointment, Timestamp> lastUpdateCol;
    @FXML private TableColumn<Appointment, String> lastUpdatedByCol;
    @FXML private TableColumn<Appointment,Integer> customerIdCol;
    @FXML private TableColumn<Appointment,Integer> userIdCol;
    @FXML private TableColumn<Appointment,Integer> contactIdCol;

    public static Appointment selectedAppointment;
    public static ObservableList<Appointment> appointments;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table column properties
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        // Listens for a appointment selection change
        appointmentsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, part, t1) -> {
            selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        });

        // Queries the database and saves all the appointments/users/contacts to an observable list.
        // Populates the appointments table view with appointments-
        try {
            appointments = AppointmentQueries.getAllAppointments();
            appointmentsTable.setItems(appointments);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
