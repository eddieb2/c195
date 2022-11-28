package src.model;

public class ReportByType {
    private String type;
    private Integer total;
    private String month;

    public ReportByType(String type, Integer total, String month) {
        this.type = type;
        this.total = total;
        this.month = month;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
