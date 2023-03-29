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
import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.model.LogInModel;
import lk.ijse.project_dkf.model.NewACModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

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
        Navigation.navigation(Rout.LOGIN,root);
    }

    @FXML
    void signUpMainBtnOnActon(ActionEvent event) throws IOException {
        User user=new User(usrTxt.getText(),pwTxt.getText(),eMailTxt.getText(),PhoneTxt.getText());
        NewACModel.isDuplicate(user);
        Navigation.navigation(Rout.LOGIN,root);
    }
}
