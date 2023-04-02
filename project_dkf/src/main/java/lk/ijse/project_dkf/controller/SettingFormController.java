package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingFormController implements Initializable {
    @FXML
    private TextField addresseditProfileTxt;

    @FXML
    private TextField conformPwTxt;

    @FXML
    private TextField eMailTxt;

    @FXML
    private TextField editProfileNewPwTxt;

    @FXML
    private TextField editProfileOldPwTxt;

    @FXML
    private TextField editprofileContactTxt;

    @FXML
    private TextField newUsrNameTxt;

    @FXML
    private Button updateProfileBtn;

    @FXML
    private Button updatePwBtn;

    @FXML
    private Text usrtxtField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
    }

    void setValues(){
        usrtxtField.setText(LogInFormController.user.getUserName());
    }

    @FXML
    void updateProfileBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updatePwBtnOnAction(ActionEvent event) {

    }

}
