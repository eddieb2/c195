package src.model;

public class FirstLevelDivision {
    private Integer divisionId;
    private String division;
    private String country;
    private Integer countryId;

    public FirstLevelDivision(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public FirstLevelDivision(Integer divisionId, String division, String country, Integer countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;
        this.countryId = countryId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }


}
