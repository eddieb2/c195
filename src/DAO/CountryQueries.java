package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryQueries {

    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countries = FXCollections.observableArrayList();

        String sql = "SELECT * FROM countries";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Integer countryId = rs.getInt("country_id");
            String country = rs.getString("country");

            countries.add(new Country(countryId, country));
        }

        return countries;
    }

    public static Country getCountryById(Integer id) throws SQLException {
        Country country = new Country();

        String sql = "SELECT * FROM countries WHERE country_id = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer countryId = rs.getInt("country_id");
            String countryName = rs.getString("country");

            country.setCountryId(countryId);
            country.setCountry(countryName);
        }

        return country;
    }
}
