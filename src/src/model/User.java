package src.model;

import java.sql.Timestamp;

/**
 * Model for Users
 */
public class User {
    private Integer userId;
    private String userName;
    private String password; // not secure - needs encryption
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    /**
     * Used when authenticating a user.
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Used when creating a new user.
     * @param userId
     * @param userName
     * @param password
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public User(Integer userId, String userName, String password, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return
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
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Overrides the toString() method inorder to display the class in the specified way.
     * @return
     */
    @Override
    public String toString() {
        return  "#" + this.userId + " (" + this.userName + ")";
    }
}
