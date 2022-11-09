package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import Database.LoginQuery;
import java.sql.SQLException;

public class LoginScreen {
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;
    private static boolean authenticationStatus = false;

    public static boolean getAuthStatus() {
        return authenticationStatus;
    }

    private String getUserName() {
        return userNameField.getText();
    }

    private String getPassword() {
        return passwordField.getText();
    }

    public void onSubmit(ActionEvent actionEvent) throws SQLException {
        String username = getUserName();
        String password = getPassword();

        // Alerts
        Alert loginSuccess = new Alert(Alert.AlertType.INFORMATION);
        Alert loginFailed = new Alert(Alert.AlertType.ERROR);
        loginSuccess.setContentText("User logged in successfully!");
        loginFailed.setContentText("Invalid username or password!");

        authenticationStatus = LoginQuery.authenticateUser(username,password);

        if (authenticationStatus) {
            loginSuccess.show();

        } else {
            loginFailed.show();
        }

    }
}

/*
EXTRAS:
* Add password encryption
* Improve input validation
* */