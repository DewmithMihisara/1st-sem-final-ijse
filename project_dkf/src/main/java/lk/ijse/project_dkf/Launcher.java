package lk.ijse.project_dkf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;
public class Launcher extends Application {
    double x;
    double y;
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/logInForm.fxml")));
        stage.initStyle(StageStyle.UNDECORATED);
        parent.setOnMousePressed(event -> { x = event.getSceneX();y = event.getSceneY(); });
        parent.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        stage.setScene(new Scene(parent));
        stage.setTitle("DKF");
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.show();
    }
}
