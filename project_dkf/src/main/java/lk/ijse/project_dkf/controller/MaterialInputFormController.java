package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class MaterialInputFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    void cutInBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.CUT_IN,pane);
    }

    @FXML
    void resevedBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.RESEVED_IN,pane);

    }
}