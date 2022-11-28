package src.model;

public class ReportByCountry {
    private String country;
    private Integer total;

    public ReportByCountry(String country, Integer count) {
        this.country = country;
        this.total = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

