package src.model;

/**
 * Model for Countries
 */
public class Country {
    private Integer countryId;
    private String country;

    /**
     * Default constructor
     */
    public Country() {}

    /**
     * @param countryId
     * @param country
     */
    public Country(Integer countryId, String country) {
        this.countryId = countryId;
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
     * Overrides the toString() method inorder to display the class in the specified way.
     * @return
     */
    @Override
    public String toString() {
        return this.country;
    }
}
