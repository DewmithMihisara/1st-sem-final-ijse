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

public class NewAcFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField PhoneTxt;

    @FXML
    private TextField eMailTxt;

    @FXML
    private TextField pwTxt;

    @FXML
    private Button signInBtn;

    @FXML
    private Button signUpMainBtn;

    @FXML
    private TextField usrTxt;

    @FXML
    void signInBtnOnActon(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader
                .load(getClass()
                        .getResource("/view/logInForm.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("SignIn");
        stage.setScene(scene);
    }

    @FXML
    void signUpMainBtnOnActon(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader
                .load(getClass()
                        .getResource("/view/logInForm.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("SignIn");
        stage.setScene(scene);
    }
}
