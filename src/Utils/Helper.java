package utils;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Helper {
    public static void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
