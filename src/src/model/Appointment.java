package src.model;

import java.sql.Date;

/*
Appointment_ID INT(10) (PK)
Title VARCHAR(50)
Description VARCHAR(50)
Location VARCHAR(50)
Type VARCHAR(50)
Start DATETIME
End DATETIME
Create_Date DATETIME
Created_By VARCHAR(50)
Last_Update TIMESTAMP
Last_Updated_By VARCHAR(50)
Customer_ID INT(10) (FK)
User_ID INT(10) (FK)
Contact_ID INT(10) (FK)
*/
public class Appointment {
    private Integer appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;
//    private Date createDate;
//    private String createdBy;
//    private Date lastUpdate;
//    private String lastUpdatedBy;
    private Integer customerId;
    private Integer userId;
    private Integer contactId;

    public Appointment(Integer appointmentId, String title, String description, String location, String type, Date start, Date end, Integer customerId, Integer userId, Integer contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return appointmentId
     */
    public Integer getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId
     */
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return type
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
     * @return start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end
     */
    public void setEnd(Date end) {
        this.end = end;
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
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return contactId
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
}