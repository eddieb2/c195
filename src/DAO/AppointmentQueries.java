package DAO;

import src.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.Customer;

import java.sql.*;

public class AppointmentQueries {

    /**
     * READ
     * @return
     * @throws SQLException
     */
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
            Timestamp start = rs.getTimestamp("start");
            Timestamp end = rs.getTimestamp("end");
            Timestamp createDate = rs.getTimestamp("create_date");
            String createdBy = rs.getString("created_by");
            Timestamp lastUpdate = rs.getTimestamp("last_update");
            String lastUpdatedBy = rs.getString("last_updated_by");
            Integer customerId = rs.getInt("customer_id");
            Integer userId = rs.getInt("user_id");
            Integer contactId = rs.getInt("contact_id");

            appointments.add(new Appointment(appointmentId,title,description,location,type,start,end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId,userId,contactId));
        }

        return appointments;
    }

    /**
     * CREATE
     * @param appointment
     * @throws SQLException
     */
    public static void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments(title, description, location, type, start, end, create_date, created_by, last_update, last_updated_by, customer_id, user_id, contact_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4,appointment.getType());
        ps.setTimestamp(5, appointment.getStart());
        ps.setTimestamp(6, appointment.getEnd());
        ps.setTimestamp(7, appointment.getCreateDate());
        ps.setString(8, appointment.getCreatedBy());
        ps.setTimestamp(9, appointment.getLastUpdate());
        ps.setString(10, appointment.getLastUpdatedBy());
        ps.setInt(11, appointment.getCustomerId());
        ps.setInt(12, appointment.getUserId());
        ps.setInt(13,appointment.getContactId());

        ps.execute();
    }

    /**
     * DELETE
     * @param appointment
     * @throws SQLException
     */
    public static void deleteAppointmentById(Appointment appointment) throws SQLException {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, appointment.getAppointmentId());

        ps.execute();
    }

    /**
     * DELETE
     * @param customer
     */
    public static void deleteAllAppointmentsByCustomerId(Customer customer) {

    }

    /**
     * UPDATE
     * @param appointment
     */
    public static void updateAppointment(Appointment appointment) {

    }
}
