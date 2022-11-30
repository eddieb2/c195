package DAO;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Methods that connect/disconnect the application to the database.
 */
public abstract class DBConnection {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface


    /**
     * Opens a connection to the database.
     */
    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection successful!\n");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Closes a connection to the database.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
