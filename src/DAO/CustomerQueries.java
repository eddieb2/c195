package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerQueries {

    /**
     * Read all customers.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customers";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Integer customerId = rs.getInt("customer_id");
            String customerName = rs.getString("customer_name");
            String customerAddress = rs.getString("address");
            String postalCode = rs.getString("postal_code");
            String phone = rs.getString("phone");
            Integer divisionId = rs.getInt("division_id");

            Timestamp createDate = rs.getTimestamp("create_date");
            String createdBy = rs.getString("created_by");
            Timestamp lastUpdate = rs.getTimestamp("last_update");
            String lastUpdatedBy = rs.getString("last_updated_by");

            customers.add(new Customer(customerId, customerName, customerAddress, postalCode, phone, divisionId, createDate, createdBy, lastUpdate, lastUpdatedBy));
        }

        return customers;
    }


    /**
     * Create new customer.
     * @param customer
     */
    public static void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers(customer_name, address, postal_code, phone, division_id, create_date, created_by, last_update, last_updated_by) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(5, customer.getDivisionId());
        ps.setTimestamp(6, customer.getCreateDate());
        ps.setString(7, customer.getCreatedBy());
        ps.setTimestamp(8, customer.getLastUpdate());
        ps.setString(9, customer.getLastUpdatedBy());

        ps.execute();
    }

    /**
     * Delete a customer.
     * @param customer
     * @throws SQLException
     */
    public static void deleteCustomer(Customer customer) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, customer.getCustomerId());

        ps.execute();

    }
}
