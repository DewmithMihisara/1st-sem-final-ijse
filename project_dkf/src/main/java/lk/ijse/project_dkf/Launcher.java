package lk.ijse.project_dkf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/logInForm.fxml")));
        stage.setScene(new Scene(parent));
        stage.setTitle("DKF");
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.show();
    }
}
