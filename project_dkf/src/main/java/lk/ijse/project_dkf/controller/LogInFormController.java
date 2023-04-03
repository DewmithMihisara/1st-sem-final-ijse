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
    public static User user;
    @FXML
    void frgtPwBtnOnActon(ActionEvent event) {
    }
    @FXML
    void sgnMainBtnOnAction(ActionEvent event) throws IOException {
//        String usrName =usrTxt.getText();
//        String password = pwTxt.getText();
//
//        if (usrName.equals("") ||password.equals("")){
//            new Alert(Alert.AlertType.ERROR,
//                    "Please fill all Details!")
//                    .show();
//        }else {
//            try {
//                user= LogInModel.isCorrect(usrName);
//                if (user.getUserName().equals(usrName) && user.getPassword().equals(password)){
//                    Navigation.navigation(Rout.DASHBOARD,root);
//                }else {
//                    new Alert(Alert.AlertType.ERROR,
//                            "Password is wrong!")
//                            .show();
//                    pwTxt.clear();
//                }
//            } catch (Exception e) {
//                new Alert(Alert.AlertType.ERROR,
//                        "User name is wrong!")
//                        .show();
//                usrTxt.clear();
//                pwTxt.clear();
//            }
//        }
        Navigation.navigation(Rout.DASHBOARD,root);
    }
    @FXML
    void signUpBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.NEW_AC,root);
    }
}
