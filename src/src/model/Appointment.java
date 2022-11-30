package src.model;

import java.time.LocalDateTime;

/**
 * Model for Appointments
 */
public class Appointment {
    private Integer appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private Integer customerId;
    private Integer userId;
    private Integer contactId;

    /**
     * Used when retrieving data from the database.
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     */
    public Appointment(Integer appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customerId, Integer userId, Integer contactId) {
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
     * Used when inserting an appointment into the database. The database automatically creates an appointmentId.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     */
    public Appointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customerId, Integer userId, Integer contactId) {
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
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end
     */
    public void setEnd(LocalDateTime end) {
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
