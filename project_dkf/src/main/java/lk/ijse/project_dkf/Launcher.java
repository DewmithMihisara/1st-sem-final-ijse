package lk.ijse.project_dkf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent= FXMLLoader.load(getClass().getResource("/view/logIn.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Log In");
        stage.centerOnScreen();

        stage.show();
    }
}