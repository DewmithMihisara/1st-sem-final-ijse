package lk.ijse.project_dkf.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private Button logOutBtn;

    @FXML
    private AnchorPane root;

    @FXML
    private Button userBtn;

    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader
                .load(getClass()
                        .getResource("/view/logInForm.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("SignIn");
        stage.setScene(scene);
    }

    @FXML
    void userBtnOnAction(ActionEvent event) {

    }

}
