import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DAO.DBConnection;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
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
