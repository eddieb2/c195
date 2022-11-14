package DAO;

import src.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQueries {

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String sql = "SELECT customer_id, customer_name, address, postal_code, phone, division_id FROM customers";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();



        while(rs.next()) {
            Integer customerId = rs.getInt("customer_id");
            String customerName = rs.getString("customer_name");
            String customerAddress = rs.getString("address");
            String postalCode = rs.getString("postal_code");
            String phone = rs.getString("phone");
            Integer divisionId = rs.getInt("division_id");

            customers.add(new Customer(customerId,customerName,customerAddress,postalCode,phone, divisionId));
        }

        return customers;
    }

}
