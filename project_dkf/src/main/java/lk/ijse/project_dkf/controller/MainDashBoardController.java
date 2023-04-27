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
import lk.ijse.project_dkf.model.LogHistoryModel;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.NewWindowNavigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.voiceAssistant.Assistant;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import static lk.ijse.project_dkf.util.Rout.*;

public class MainDashBoardController implements Initializable{
    @FXML
    private Label assLbl;
    @FXML
    private Button retailBtn;
    @FXML
    private Button logOutBtn;
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
    private Button assBtn;
    Assistant assistant;
    @FXML
    void assistantOnAction(ActionEvent event) throws IOException {
        assistant = new Assistant();
        String command = assistant.assistant();


        if (command !=null){
            assLbl.setText("can't catch it. say again");
        } else if (command.contains("SETTINGS")) {
            Navigation.navigation(Rout.USER_SETTINGS,midleStage);
        } else if (command.contains("ORDER")) {
            Navigation.navigation(ORDER,root);
        } else if (command.contains("NEW ORDER")) {
            NewWindowNavigation.windowNavi(NEW_ORDER);
        } else if (command.contains("INPUT")) {
            NewWindowNavigation.windowNavi(CUT_IN);
        }else if (command.contains("OUTPUT")){
            NewWindowNavigation.windowNavi(OUTPUT);
        } else if (command.contains("PACKING")) {
            NewWindowNavigation.windowNavi(PAKING);
        }else {
            assLbl.setText("No command Found, Bye!");
        }
    }
    @FXML
    void testBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(DASHBOARD,root);
    }
    @FXML
    void retailBtnOnAction(ActionEvent event) {
        new animatefx.animation.Shake(retailBtn).play();
        PopUps.popUps(AlertTypes.ERROR,"Under Development","This option is in under development. \nExpect this feature in a future update.");
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
        PopUps.popUps(AlertTypes.ERROR,"Under Development","This option is in under development. \nExpect this feature in a future update.");
    }
    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        LogInFormController.logHistory.setLogOut(LocalDateTime.now());
        try {
            LogHistoryModel.save(LogInFormController.logHistory);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Navigation.navigation(LOGIN,root);
    }
    @FXML
    void settingBtnOnActon(ActionEvent event) throws IOException {
        bkBtn.setVisible(true);
        Navigation.navigation(USER_SETTINGS,midleStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTimeLbl();

    }
    private void setTimeLbl() {
        SetTime.setTime(timeTxt);
    }
}
