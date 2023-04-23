package lk.ijse.project_dkf.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTimeLbl();
    }

    private void setTimeLbl() {
        SetTime.setTime(timeTxt);
    }
}
