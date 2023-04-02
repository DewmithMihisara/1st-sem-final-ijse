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
import lk.ijse.project_dkf.model.NewACModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.sql.SQLException;

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
        Navigation.navigation(Rout.LOGIN,root);
    }

    @FXML
    void signUpMainBtnOnActon(ActionEvent event) throws IOException {
        if (usrTxt.getText().equals("") || eMailTxt.getText().equals("") || pwTxt.getText().equals("") || PhoneTxt.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,
                    "Please Fill all Details!")
                    .show();
        }else{
            User user=new User(usrTxt.getText(),pwTxt.getText(),eMailTxt.getText(),PhoneTxt.getText());
            try {
                boolean affectedRows =NewACModel.isDuplicate(user);
                if (affectedRows ) {
                    new Alert(Alert.AlertType.CONFIRMATION,
                            "User Add!")
                            .show();
                    Navigation.navigation(Rout.LOGIN,root);
                }
            } catch (SQLException e) {
                usrTxt.clear();
                new Alert(Alert.AlertType.ERROR,
                        "User name alredy added!")
                        .show();
            }
        }
    }
}
