package src.model;

/**
 * Model for FirstLevelDivision
 */
public class FirstLevelDivision {
    private Integer divisionId;
    private String division;
    private String country;
    private Integer countryId;

    /**
     * @param divisionId
     */
    public FirstLevelDivision(Integer divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @param divisionId
     * @param division
     * @param country
     * @param countryId
     */
    public FirstLevelDivision(Integer divisionId, String division, String country, Integer countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;
        this.countryId = countryId;
    }

    /**
     * @return
     */
    public Integer getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId
     */
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * @param countryId
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * Overrides the toString() method inorder to display the class in the specified way.
     * @return
     */
    @Override
    public String toString() {
        return  this.division;
    }
}
