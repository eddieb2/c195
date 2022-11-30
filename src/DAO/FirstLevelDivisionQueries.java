package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.FirstLevelDivision;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Methods for retrieving FirstLevelDivision data from the database.
 */
public class FirstLevelDivisionQueries {

    /**
     * Retrieves all FirstLevelDivisions from the database.
     * @return ObservableList<FirstLevelDivision>
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();

        String sql = "SELECT f.division_id, f.division, c.country, c.country_id FROM first_level_divisions as f\n" +
                "\tJOIN countries AS c ON f.country_id = c.country_id;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Integer divisionId = rs.getInt("division_id");
            String division = rs.getString("division");
            String country = rs.getString("country");
            Integer countryId = rs.getInt("country_id");
            divisions.add(new FirstLevelDivision(divisionId, division, country, countryId));
        }

        return divisions;
    }

    /**
     * Retrieves a specified FirstLevelDivision by id.
     * @param divisionId
     * @return FirstLevelDivision
     * @throws SQLException
     */
    public static FirstLevelDivision getDivisionById(Integer divisionId) throws SQLException {
        FirstLevelDivision division = new FirstLevelDivision(divisionId);

        String sql = "SELECT f.division_id, f.division, c.country FROM first_level_divisions as f\n" +
                "\tJOIN countries AS c ON f.country_id = c.country_id WHERE division_id = ?;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            division.setDivisionId(divisionId);
            division.setDivision(rs.getString("division"));
            division.setCountry(rs.getString("country"));
        }

        return division;
    }
}
