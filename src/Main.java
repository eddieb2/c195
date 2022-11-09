import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import helper.JBDC;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // JBDC connection test
        JBDC.openConnection();
        JBDC.closeConnection();

        Parent root = FXMLLoader.load(getClass().getResource("view/mainScreen.fxml"));
        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
