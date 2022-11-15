package src.model;

public class Session {
    private User autheticatedUser;

    public Session() {

    }

    public Session(User autheticatedUser) {
        this.autheticatedUser = autheticatedUser;
    }

    public User getAutheticatedUser() {
        return autheticatedUser;
    }

    public void setAutheticatedUser(User autheticatedUser) {
        this.autheticatedUser = autheticatedUser;
    }
}
