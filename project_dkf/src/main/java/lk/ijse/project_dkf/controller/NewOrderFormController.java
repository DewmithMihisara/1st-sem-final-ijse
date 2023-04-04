package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.NewWindowNavigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class NewOrderFormController {
    @FXML
    private AnchorPane root;
    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.ORDER, root);
    }
    @FXML
    void newBuyerBtnOnAction(ActionEvent event) throws IOException {
        NewWindowNavigation.windowNavi(Rout.BUYER);
    }
}
