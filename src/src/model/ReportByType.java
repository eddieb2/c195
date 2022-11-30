package src.model;

/**
 * Model for ReportByType
 * -- used in the reports section of the application
 * -- displays type, total and month
 */
public class ReportByType {
    private String type;
    private Integer total;
    private String month;

    /**
     * @param type
     * @param total
     * @param month
     */
    public ReportByType(String type, Integer total, String month) {
        this.type = type;
        this.total = total;
        this.month = month;

    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     * @return
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }
}
