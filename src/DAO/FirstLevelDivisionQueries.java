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

        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Integer divisionId = rs.getInt("division_id");
            String division = rs.getString("division");
            Integer countryId = rs.getInt("country_id");

            divisions.add(new FirstLevelDivision(divisionId, division, countryId));
        }

        return divisions;
    }
}
