package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionQueries {

    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();

        String sql = "SELECT f.division_id, f.division, c.country FROM first_level_divisions as f\n" +
                "\tJOIN countries AS c ON f.country_id = c.country_id;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Integer divisionId = rs.getInt("division_id");
            String division = rs.getString("division");
            String country = rs.getString("country");

            divisions.add(new FirstLevelDivision(divisionId, division, country));
        }

        return divisions;
    }
}
