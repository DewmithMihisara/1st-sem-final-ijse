package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class OrderFormController {
    @FXML
    private AnchorPane root;
    @FXML
    void newOrderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.NEW_ORDER,root);
    }
}
