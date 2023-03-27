package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn {
    @FXML
    private Button frgtPwBtn;

    @FXML
    private TextField pwTxt;

    @FXML
    private AnchorPane root;

    @FXML
    private Button sgnBtn;

    @FXML
    private Button sgnUpBtn;

    @FXML
    private TextField usrTxt;

    @FXML
    void signUpOnActon(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/newAcForm.fxml"));

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Create new Account");
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }

}
