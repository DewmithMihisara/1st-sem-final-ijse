package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class ResevedInFormController {
    @FXML
    private TextField lTxt;

    @FXML
    private TextField mTxt;

    @FXML
    private ComboBox<?> materialCmbBx;

    @FXML
    private TextField needItmTxt;

    @FXML
    private ComboBox<?> orderIdCmbBox;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField sTxt;

    @FXML
    private TextField xlTxt;

    @FXML
    private TextField xxlTxt;

    @FXML
    void cutInBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.CUT_IN,pane);
    }

    @FXML
    void materialInBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.MATERIAL_IN,pane);
    }
}
