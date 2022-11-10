package View_Controller;

import DAO.UserAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


public class Login implements Initializable {
    @FXML private Label welcomeLabel;
    @FXML private Button loginButton;
    @FXML private Label locationLabel;
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;
    private static boolean authenticationStatus = false;

    public static boolean getAuthStatus() {
        return authenticationStatus;
    }

    /*
     * Retrieves the user input for the username field
     * */
    private String getUserName() {
        return userNameField.getText();
    }

    /*
    * Retrieves the user input for the password field
    * */
    private String getPassword() {
        return passwordField.getText();
    }

    /*
    * Redirects the user to the main screen after successful authentication
    * */
    private void redirect() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/Dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    /*
    * Logs the user into the scheduling application
    * */
    public void onSubmit() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Lang/Nat", Locale.getDefault());

            String username = getUserName();
            String password = getPassword();

            // Alerts
            Alert loginSuccess = new Alert(Alert.AlertType.INFORMATION);
            Alert loginFailed = new Alert(Alert.AlertType.ERROR);
            loginSuccess.setContentText(rb.getString("Success"));
            loginFailed.setContentText(rb.getString("Failed"));

            authenticationStatus = UserAccess.authenticateUser(username,password);

            if (authenticationStatus) {
                redirect();
            } else {
                loginFailed.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ZoneId zone = ZoneId.systemDefault();
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);

            // Sets the location in the UI
            locationLabel.setText(zone.toString());

            rb = ResourceBundle.getBundle("Lang/Nat", Locale.getDefault());
            userNameField.setPromptText(rb.getString("Username"));
            passwordField.setPromptText(rb.getString("Password"));
            loginButton.setText(rb.getString("Submit"));
            welcomeLabel.setText(rb.getString("Welcome!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
EXTRAS:
* Add password encryption
* Improve input validation
* Change the login button text to "login" if necessary.
* */


/* Opens new window
*         Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();

        * */