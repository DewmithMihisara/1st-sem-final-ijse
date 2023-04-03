package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OrdrFormController {
    @FXML
    private Button addByrBtn;
    @FXML
    void addByrBtnOnAction(ActionEvent event) throws IOException {
        Parent parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/logInForm.fxml")));
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("DKF");
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.show();
    }
}
