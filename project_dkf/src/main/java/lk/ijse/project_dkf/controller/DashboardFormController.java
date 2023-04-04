package lk.ijse.project_dkf.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;
@Getter
public class DashboardFormController implements Initializable {
    @FXML
    private Button bkBtn;
    @FXML
    private AnchorPane midleStage;
    @FXML
    private Button logOutBtn;

    @FXML
    private AnchorPane root;
    @FXML
    private Text timeTxt;
    @FXML
    private Button userBtn;

    @FXML
    private Button orderBtn;
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
    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.ORDER,midleStage);
    }
    @FXML
    void buyerBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.BUYER,midleStage);
    }
    @FXML
    void outputBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.OUTPUT,midleStage);
    }
    @FXML
    void packingBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.PAKING,midleStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTime();
    }
    private void setTime() {
        Thread thread=new Thread(() -> {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("hh:mm:ss");
            while (true){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timeNow = simpleDateFormat.format(new Date());
                Platform.runLater(()->{
                    timeTxt.setText(timeNow);
                });
            }
        });
        thread.start();
    }
}
