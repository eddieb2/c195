package src.model;

/**
 * Model for ReportByCountry
 * -- used in the reports section of the application
 * -- displays total customers by country
 */
public class ReportByCountry {
    private String country;
    private Integer total;

    /**
     * @param country
     * @param count
     */
    public ReportByCountry(String country, Integer count) {
        this.country = country;
        this.total = count;
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
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
}

