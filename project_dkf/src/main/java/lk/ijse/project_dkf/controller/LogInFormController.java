package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.model.LogInModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.sql.SQLException;

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
        String usrName =usrTxt.getText();
        String password = pwTxt.getText();

        try {
            User user= LogInModel.isCorrect(usrName);
            if (user.getUserName().equals(usrName) && user.getPassword().equals(password)){
                Navigation.navigation(Rout.DASHBOARD,root);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void signUpBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.NEW_AC,root);
    }
}
