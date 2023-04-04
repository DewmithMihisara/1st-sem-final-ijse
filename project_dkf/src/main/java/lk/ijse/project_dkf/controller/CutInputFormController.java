package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class CutInputFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    void materialBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.MATERIAL_IN,pane);
    }

    @FXML
    void resevedBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.RESEVED_IN,pane);
    }
}
