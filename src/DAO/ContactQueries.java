package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Methods for retrieving Contact data from the database.
 */
public class ContactQueries {

    /**
     * Retrieves all contacts from the database.
     * @return ObservableList<Contact>
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer contactId = rs.getInt("contact_id");
            String contactName = rs.getString("contact_name");
            String email = rs.getString("email");

            Contact newContact = new Contact(contactId, contactName, email);
            contacts.add(newContact);
        }

        return contacts;
    }

    /**
     * Retrieves all contact ids from the database.
     * @return ObservableList<Integer>
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllContactIds() throws SQLException {
        ObservableList<Integer> contactIds = FXCollections.observableArrayList();

        String sql = "SELECT contact_id FROM contacts ORDER BY contact_id";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer contactId = rs.getInt("contact_id");
            contactIds.add(contactId);
        }

        return contactIds;
    }
}
