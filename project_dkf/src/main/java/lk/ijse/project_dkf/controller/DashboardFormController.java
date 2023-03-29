package lk.ijse.project_dkf.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    private Button bkBtn;
    @FXML
    private AnchorPane midleStage;
    @FXML
    private Button logOutBtn;

    @FXML
    private AnchorPane root;

    @FXML
    private Button userBtn;

    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.LOGIN,root);
    }
    @FXML
    void userBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.USER_SETTINGS,midleStage);
    }
    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.DASHBOARD,root);
    }
}
