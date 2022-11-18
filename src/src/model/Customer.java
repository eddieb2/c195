package src.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Customer {

    private Integer customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private Integer divisionId;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    // Used when pulling data from the database
    public Customer(Integer customerId, String customerName, String address, String postalCode, String phone, Integer divisionId, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    // Used for creating a new customer to add to the database
    public Customer(String customerName, String address, String postalCode, String phone, Integer divisionId) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;

        this.createDate = Timestamp.valueOf(LocalDateTime.now());
        this.createdBy = "admin"; // WHOEVER IS CURRENTLY LOGGED IN --- NEEDS FIXED

        this.lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        this.lastUpdatedBy = "admin";  // WHOEVER IS CURRENTLY LOGGED IN --- NEEDS FIXED
    }

    /**
     * @return customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return divisionId
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        return  "#" + this.customerId + " (" + this.customerName + ")";
    }
}
