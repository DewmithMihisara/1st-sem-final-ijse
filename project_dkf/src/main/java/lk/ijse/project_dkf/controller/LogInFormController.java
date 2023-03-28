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

public class LogInFormController {
    @FXML
    private Button frgtPwBtn;

    @FXML
    private TextField pwTxt;

    @FXML
    private AnchorPane root;

    @FXML
    private Button sgnMainBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usrTxt;

    @FXML
    void frgtPwBtnOnActon(ActionEvent event) {

    }

    @FXML
    void sgnMainBtnOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader
                .load(getClass()
                        .getResource("/view/dashboardForm.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("HomePage");
        stage.setScene(scene);
    }

    @FXML
    void signUpBtnOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader
                .load(getClass()
                        .getResource("/view/newAcForm.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("SignUp");
        stage.setScene(scene);

    }

}
