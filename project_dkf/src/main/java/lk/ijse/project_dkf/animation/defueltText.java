package lk.ijse.project_dkf.animation;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class defueltText {
    public static void Defuelt(TextField text){
        text.setStyle(
                "-fx-background-color:tranceparent; " +
                        "-fx-text-fill: white"
        );
    }
    public static void Defuelt(ComboBox text){
        text.setStyle(
                "-fx-background-color:tranceparent; " +
                        "-fx-text-fill: white"
        );
    }
}
