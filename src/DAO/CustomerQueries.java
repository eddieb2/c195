package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.Customer;
import src.model.ReportByCountry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Methods for retrieving Customer data from the database.
 */
public class CustomerQueries {

    /**
     * Retrieves all customers from the database.
     * @return ObservableList<Customer>
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

            customers.add(new Customer(customerId, customerName, customerAddress, postalCode, phone, divisionId));
        }


        return customers;
    }

    /**
     * Retrieves all customer ids from the database.
     * @return ObservableList<Integer>
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllCustomerIds() throws SQLException {
        ObservableList<Integer> customerIds = FXCollections.observableArrayList();

        String sql = "SELECT customer_id FROM customers ORDER BY customer_id";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer customerId = rs.getInt("customer_id");
            customerIds.add(customerId);
        }

        return customerIds;
    }


    /**
     * Adds a new customer to the database.
     * @param customer
     */
    public static void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers(customer_name, address, postal_code, phone, division_id) VALUES(?,?,?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(5, customer.getDivisionId());

        ps.execute();
    }

    /**
     * Updates a specified customer.
     * @param customer
     * @throws SQLException
     */
    public static void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers \n" +
                "\tSET customer_name = ?, address = ?, postal_code = ?, phone = ?, division_id = ?\n" +
                "    WHERE customer_id = ?;";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(5,customer.getDivisionId());
        ps.setInt(6, customer.getCustomerId());

        ps.execute();
    }

    /**
     * Delete a specified customer.
     * @param customer
     * @throws SQLException
     */
    public static void deleteCustomer(Customer customer) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, customer.getCustomerId());

        ps.execute();
    }


    /**
     * Retrieves the total number of customers for each country.
     * @return ObservableList<ReportByCountry>
     * @throws SQLException
     */
    public static ObservableList<ReportByCountry> getCustomersTotalByCountry() throws SQLException {
        String sql = "select countries.Country, COUNT(*) AS total from customers AS c\n" +
                "\tJOIN first_level_divisions AS f ON c.Division_ID = f.Division_ID\n" +
                "    JOIN countries ON f.Country_ID = countries.Country_ID\n" +
                "    GROUP BY countries.country;";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<ReportByCountry> reportByCountries = FXCollections.observableArrayList();

        while (rs.next()) {
                String country = rs.getString("country");
                Integer total = rs.getInt("total");

                reportByCountries.add(new ReportByCountry(country,total));
        }

        return reportByCountries;
    }
}
