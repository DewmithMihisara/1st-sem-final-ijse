package lk.ijse.project_dkf.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.SetTime;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static lk.ijse.project_dkf.util.Rout.DASHBOARD;
import static lk.ijse.project_dkf.util.Rout.ORDER;

public class MainDashBoardController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    public static AnchorPane stage2;
    @FXML
    private Label timeTxt;
    @FXML
    void buyerBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.BUYER,stage2);
    }
    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.LOGIN,root);
    }
    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException, InterruptedException {
        Navigation.navigation(DASHBOARD,root);
    }
    @FXML
    void retailBtnOnAction(ActionEvent event) {

    }
    @FXML
    void userBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.USER_SETTINGS,stage2);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setTime();
    }
    private void setTime() {
        SetTime.setTime(timeTxt);
    }
}
