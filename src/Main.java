import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * @author Edward Blanciak
 * Class - SOFTWARE II - C195
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Lang/Nat", Locale.getDefault());

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view_controller/login/login.fxml")));

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
