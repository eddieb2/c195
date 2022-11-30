package src.model;

/**
 * Model for Customers
 */
public class Customer {

    private Integer customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private Integer divisionId;

    /**
     * Used when retrieving data from the database.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     */
    public Customer(Integer customerId, String customerName, String address, String postalCode, String phone, Integer divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /**
     * Used when adding data to the database. The database automatically creates a customerId.
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     */
    public Customer(String customerName, String address, String postalCode, String phone, Integer divisionId) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
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

    /**
     * Overrides the toString() method inorder to display the class in the specified way.
     * @return
     */
    @Override
    public String toString() {
        return  "#" + this.customerId + " (" + this.customerName + ")";
    }
}
