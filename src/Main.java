import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.model.Session;
import src.model.User;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application {
    // Sets the current logged in user for testing purposes. -- CURRENTLY NOT WORKING
    public static User testUser = new User("admin","admin");
    public static Session currentSession = new Session(testUser);

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(currentSession.getAutheticatedUser().getUserName());

        try {
            ResourceBundle rb = ResourceBundle.getBundle("Lang/Nat", Locale.getDefault());

            // For testing individual views -- switch back to login form when project complete
//            Parent root = FXMLLoader.load-(Objects.requireNonNull(getClass().getResource("View_Controller/Login.fxml")));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view_controller/dashboard/dashboard.fxml")));

            primaryStage.setTitle(rb.getString("Login"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        DBConnection.openConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}
