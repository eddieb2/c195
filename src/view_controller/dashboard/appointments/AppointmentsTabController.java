package view_controller.dashboard.appointments;

import DAO.AppointmentQueries;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import src.model.Appointment;
import src.model.Contact;
import src.model.Customer;
import src.model.User;
import utils.Helper;
import view_controller.dashboard.DashboardController;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Displays all appointments. Enables the user to add, update, or delete appointments.
 */
public class AppointmentsTabController implements Initializable {

    @FXML private AnchorPane appointmentsTab;
    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment,String> idCol;
    @FXML private TableColumn<Appointment,Integer> titleCol;
    @FXML private TableColumn<Appointment,String> descriptionCol;
    @FXML private TableColumn<Appointment,String> locationCol;
    @FXML private TableColumn<Appointment,String> typeCol;
    @FXML private TableColumn<Appointment,LocalDateTime> startTimeCol;
    @FXML private TableColumn<Appointment,LocalDateTime> endTimeCol;
    @FXML private TableColumn<Appointment,LocalDateTime> startDateCol;
    @FXML private TableColumn<Appointment,LocalDateTime> endDateCol;
    @FXML private TableColumn<Appointment,Integer> customerIdCol;
    @FXML private TableColumn<Appointment,Integer> userIdCol;
    @FXML private TableColumn<Appointment,Integer> contactIdCol;
    @FXML public RadioButton monthRadioButton;
    @FXML public RadioButton weekRadioButton;
    @FXML public RadioButton allRadioButton;
    final ToggleGroup group = new ToggleGroup();

    public static Appointment selectedAppointment;
    public static ObservableList<Appointment> appointments;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Sets the toggle group for the radio buttons
        monthRadioButton.setToggleGroup(group);
        weekRadioButton.setToggleGroup(group);
        allRadioButton.setToggleGroup(group);

        // Sets User data for the radio buttons
        monthRadioButton.setUserData("Month");
        weekRadioButton.setUserData("Week");
        allRadioButton.setUserData("All");

        // Filters the appointments by month/week/all
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                if (group.getSelectedToggle() != null) {

                    if (group.getSelectedToggle().getUserData() == "All") {
                        appointmentsTable.setItems(appointments);
                   } else if (group.getSelectedToggle().getUserData() == "Month") {

                        // Lambda #1
                        FilteredList<Appointment> filteredList = appointments.filtered(appointment -> {
                            Month aptMonth = appointment.getStart().toLocalDate().getMonth();
                            Month curMonth = LocalDate.now().getMonth();

                            if (aptMonth != curMonth) {
                                return false;
                            }
                            return true;
                        });

                        appointmentsTable.setItems(filteredList);

                    } else if (group.getSelectedToggle().getUserData() == "Week") {

                        // Lambda #2
                        FilteredList<Appointment> filteredList = appointments.filtered(appointment -> {
                            LocalDate curDate = LocalDate.now();
                            WeekFields weekFields = WeekFields.of(Locale.getDefault());

                            int curWeekNum = curDate.get(weekFields.weekOfWeekBasedYear());
                            int aptWeekNum = appointment.getStart().toLocalDate().get(weekFields.weekOfWeekBasedYear());

                            if (curWeekNum == aptWeekNum) {
                                return true;
                            }

                            return false;
                        });

                        appointmentsTable.setItems(filteredList);
                    }
                }
            }
        });

        // Table column properties
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        // Separates the date and time into their respective columns
        Helper.cellFormatterToTime(startTimeCol);
        Helper.cellFormatterToTime(endTimeCol);
        Helper.cellFormatterToDate(startDateCol);
        Helper.cellFormatterToDate(endDateCol);

        // Listens for a appointment selection change
        appointmentsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, part, t1) -> {
            selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        });

        // Queries the database and saves all the appointments/users/contacts to an observable list.
        // Populates the appointments table view with appointments
        try {
            appointments = AppointmentQueries.getAllAppointments();
            appointmentsTable.setItems(appointments);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Populates combo boxes with the appropriate data.
     * @param customerComboBox
     * @param userComboBox
     * @param contactComboBox
     * @param startTimeComboBox
     * @param endTimeComboBox
     */
    public static void populateComboBoxes(ComboBox<Customer> customerComboBox, ComboBox<User> userComboBox, ComboBox<Contact> contactComboBox, ComboBox<LocalTime> startTimeComboBox, ComboBox<LocalTime> endTimeComboBox) {
        // Populates combo boxes with classes - displays id and name (refer to the toString method in the class)
        customerComboBox.setItems(DashboardController.customers);
        userComboBox.setItems(DashboardController.users);
        contactComboBox.setItems(DashboardController.contacts);

        // Converts business hours to local time zone and populates combo boxes
        ZoneId businessZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        ZonedDateTime openZDT = LocalDate.now().atTime(8, 0).atZone(businessZone);
        ZonedDateTime closeZDT = LocalDate.now().atTime(22, 0).atZone(businessZone);

        LocalTime openHoursToLocal = openZDT.withZoneSameInstant(localZone).toLocalTime();
        LocalTime closeHoursToLocal = closeZDT.withZoneSameInstant(localZone).toLocalTime();


        while (openHoursToLocal.isBefore(closeHoursToLocal.plusSeconds(1))) {
            startTimeComboBox.getItems().add(openHoursToLocal);
            endTimeComboBox.getItems().add(openHoursToLocal);

            openHoursToLocal = openHoursToLocal.plusMinutes(15);
        }
    }
}
