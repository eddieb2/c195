package view_controller.login;

import DAO.UserQueries;
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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * This form enables a user to login.
 */
public class LoginController implements Initializable {
    @FXML private Label welcomeLabel;
    @FXML private Button loginButton;
    @FXML private Label locationLabel;
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;
    private static boolean authenticationStatus = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ZoneId zone = ZoneId.systemDefault();
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);

            // Sets the location in the UI
            locationLabel.setText(zone.toString());

            rb = ResourceBundle.getBundle("lang/Nat", Locale.getDefault());
            userNameField.setPromptText(rb.getString("Username"));
            passwordField.setPromptText(rb.getString("Password"));
            loginButton.setText(rb.getString("Submit"));
            welcomeLabel.setText(rb.getString("Welcome!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the user input for the username field.
     * @return
     */
    private String getUserName() {
        return userNameField.getText();
    }

    /**
     * Retrieves the user input for the password field.
     * @return
     */
    private String getPassword() {
        return passwordField.getText();
    }


    /**
     * Redirects the user to the main screen after successful authentication.
     * @throws IOException
     */
    private void redirect() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view_controller/dashboard/dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }


    /**
     * Logs the user into the scheduling application when the submit button is clicked.
     */
    public void onSubmit() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("lang/Nat", Locale.getDefault());

            String username = getUserName();
            String password = getPassword();

            // Alerts
            Alert loginSuccess = new Alert(Alert.AlertType.INFORMATION);
            Alert loginFailed = new Alert(Alert.AlertType.ERROR);
            loginSuccess.setContentText(rb.getString("Success"));
            loginFailed.setContentText(rb.getString("Failed"));

            authenticationStatus = UserQueries.authenticateUser(username,password);
            loginTracker(authenticationStatus, username); // tracks login attempts

            if (authenticationStatus) {
                redirect();
            } else {
                loginFailed.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Logs login attempts to login_activity.txt.
     * @param authenticationStatus
     * @param user
     */
    public void loginTracker(Boolean authenticationStatus, String user) {
        Logger log = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fh = new FileHandler("login_activity.txt", true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);

            if (authenticationStatus) {
                log.log(Level.INFO, "Login successful. User: " + user);
            } else {
                log.log(Level.WARNING, "Login failed. User: " + user);
            }

            fh.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
