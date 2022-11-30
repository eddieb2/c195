package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Methods for retrieving User data from the database.
 */
public class UserQueries {

    /**
     * Authenticates a username and password. Returns true or false.
     * @param username
     * @param password
     * @return boolean
     * @throws SQLException
     */
    public static boolean authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT User_Name, Password FROM users WHERE User_Name = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        String dbPassword = null;

        while(rs.next()) {
            dbPassword = rs.getString("Password");
        }

        if (password.equals(dbPassword)) {
            System.out.println("Successful login!");
            return true;
        }

        System.out.println("Unsuccessful login!");
        return false;
    }


    /**
     * Retrieves all users from the database.
     * @return ObservableList<User>
     * @throws SQLException
     */
    public static ObservableList<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        ObservableList<User> users = FXCollections.observableArrayList();

        while (rs.next()) {
            Integer userId = rs.getInt("user_id");
            String userName = rs.getString("user_name");
            String password = rs.getString("password");
            Timestamp createDate = rs.getTimestamp("create_date");
            String createdBy = rs.getString("created_by");
            Timestamp lastUpdate = rs.getTimestamp("last_update");
            String lastUpdatedBy = rs.getString("last_updated_by");

            User newUser = new User(userId, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            users.add(newUser);
        }

        return users;
    }

    /**
     * Retrieves all user ids from the database.
     * @return ObservableList<Integer>
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllUserIds() throws SQLException {
        ObservableList<Integer> userIds = FXCollections.observableArrayList();

        String sql = "SELECT user_id FROM users ORDER BY user_id";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer userId = rs.getInt("user_id");
            userIds.add(userId);
        }

        return userIds;
    }
}
