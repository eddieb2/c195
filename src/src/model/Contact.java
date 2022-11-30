package src.model;

/**
 * Model for Contacts
 */
public class Contact {
    private Integer contactId;
    private String contactName;
    private String email;

    /**
     * Default constructor
     */
    public Contact() {}

    /**
     * @param contactId
     * @param contactName
     * @param email
     */
    public Contact(Integer contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * @return
     */
    public Integer getContactId() {
        return contactId;
    }

    /**
     * @param contactId
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    /**
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Overrides the toString() method inorder to display the class in the specified way.
     * @return
     */
    @Override
    public String toString() {
        return  "#" + this.contactId + " (" + this.contactName + ")";
    }
}
