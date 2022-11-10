package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccessObject {

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

}
