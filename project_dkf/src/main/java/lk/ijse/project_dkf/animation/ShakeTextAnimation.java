package lk.ijse.project_dkf.animation;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ShakeTextAnimation {
    public static void ShakeText(TextField text){
        text.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-width: 2px ;" +
                        "-fx-background-color: tranceparent ;" +
                        "-fx-text-fill : white;"
        );
        new animatefx.animation.Shake(text).play();
    }
    public static void ShakeText(ComboBox text){
        text.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-width: 2px ;" +
                        "-fx-background-color: tranceparent ;" +
                        "-fx-text-fill : white;"
        );
        new animatefx.animation.Shake(text).play();
    }
}
