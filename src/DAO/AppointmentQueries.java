package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.Appointment;
import src.model.ReportByType;
import utils.TimeConverter;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Methods for retrieving Appointment data from the database.
 */
public class AppointmentQueries {

    /**
     * Retrieves all appointments from the database.
     * @return ObservableList<Appointment>
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

            // FIXME: 11/21/2022 This can probably be refactored since changing the database to UTC
            // HANDLES INCOMING UTC TO LOCAL TIME ZONE CONVERSION
            Date startDate = rs.getDate("start");
            Time startTime = rs.getTime("start");
            LocalDateTime startDateTime = LocalDateTime.of(startDate.toLocalDate(), startTime.toLocalTime());

            Date endDate = rs.getDate("end");
            Time endTime = rs.getTime("end");
            LocalDateTime endDateTime = LocalDateTime.of(endDate.toLocalDate(), endTime.toLocalTime());

            LocalDateTime convertedStartLDT = TimeConverter.utcToLocalLDT(startDateTime);
            LocalDateTime convertedEndLDT = TimeConverter.utcToLocalLDT(endDateTime);

            Integer customerId = rs.getInt("customer_id");
            Integer userId = rs.getInt("user_id");
            Integer contactId = rs.getInt("contact_id");

            appointments.add(new Appointment(appointmentId, title, description, location, type, convertedStartLDT,
                    convertedEndLDT, customerId, userId, contactId));

        }

        return appointments;
    }

    /**
     * Adds a new appointment to the database.
     * @param appointment
     * @throws SQLException
     */
    public static void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments(title, description, location, type, start, end, customer_id, user_id, contact_id) VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4,appointment.getType());



        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));

//        ps.setTimestamp(5, Timestamp.valueOf(TimeConverter.localToUtcLDT((appointment.getStart())))); // handles timezone conversion
//        ps.setTimestamp(6, Timestamp.valueOf(TimeConverter.localToUtcLDT((appointment.getEnd())))); // handles timezone conversion

        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());
        ps.setInt(9,appointment.getContactId());

        ps.execute();
    }

    /**
     * Deletes a specified appointment.
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
     * Updates a specified appointment.
     * @param appointment
     */
    public static void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointments SET title = ?, description = ?, location = ?, type = ?, start = ?, end = ?, customer_id = ?, user_id = ?, contact_id = ? WHERE appointment_id = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());

        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));

//        ps.setTimestamp(5, Timestamp.valueOf(TimeConverter.localToUtcLDT((appointment.getStart())))); // handles timezone conversion
//        ps.setTimestamp(6, Timestamp.valueOf(TimeConverter.localToUtcLDT((appointment.getEnd())))); // handles timezone conversion
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());
        ps.setInt(9, appointment.getContactId());
        ps.setInt(10, appointment.getAppointmentId());

        ps.execute();
    }

    /**
     * Retrieves an observable list that contains the appointment type and total occurrences by month.
     * @return ObservableList<ReportByType>
     * @throws SQLException
     */
    public static ObservableList<ReportByType> getAppointmentTotalsByMonth() throws SQLException {
        String sql = "SELECT MONTHNAME(start) AS month, type, count(type) AS total from appointments group by type, MONTHNAME(start);";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<ReportByType> reportByTypes = FXCollections.observableArrayList();

        while (rs.next()) {
            String type = rs.getString("type");
            Integer total = rs.getInt("total");
            String month = rs.getString("month");

            reportByTypes.add(new ReportByType(type, total, month));
        }

        return reportByTypes;
    }
}
