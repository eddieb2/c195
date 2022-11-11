package DAO;

import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentQueries {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Integer  appointmentId = rs.getInt("appointment_id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            String location= rs.getString("location");
            String type = rs.getString("type");
            Date start = rs.getDate("start");
            Date end = rs.getDate("end");
            Integer customerId = rs.getInt("customer_id");
            Integer userId = rs.getInt("user_id");
            Integer contactId = rs.getInt("contact_id");

            appointments.add(new Appointment(appointmentId,title,description,location,type,start,end,customerId,userId,contactId));
        }

        return appointments;
    }

}
