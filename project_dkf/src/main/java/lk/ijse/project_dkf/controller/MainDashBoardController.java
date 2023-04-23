package lk.ijse.project_dkf.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.SetTime;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static lk.ijse.project_dkf.util.Rout.*;

public class MainDashBoardController implements Initializable{
    @FXML
    private Button bkBtn;
    @FXML
    private Label timeTxt;
    @FXML
    private AnchorPane midleStage;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView settingImg;
    @FXML
    private Button employeeBtn;
    @FXML
    void testBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(DASHBOARD,root);
    }
    @FXML
    void buyerBtnOnAction(ActionEvent event) throws IOException {
        bkBtn.setVisible(true);
        Navigation.navigation(BUYER,midleStage);
    }
    @FXML
    void settingMouseEnterOnAction(MouseEvent event) {
        new animatefx.animation.RotateIn(settingImg).play();
    }
    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(MAIN_DASHBOARD,root);
    }
    @FXML
    void employeeBtnOnAction(ActionEvent event) {
        new animatefx.animation.Shake(employeeBtn).play();
        new Alert(Alert.AlertType.ERROR,
                "this Option under Dewelopment")
                .show();
    }
    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(LOGIN,root);
    }
    @FXML
    void settingBtnOnActon(ActionEvent event) throws IOException {
        bkBtn.setVisible(true);
        Navigation.navigation(USER_SETTINGS,midleStage);
    }
    @FXML
    void clzBtnOnAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTimeLbl();
    }
    private void setTimeLbl() {
        SetTime.setTime(timeTxt);
    }
}
