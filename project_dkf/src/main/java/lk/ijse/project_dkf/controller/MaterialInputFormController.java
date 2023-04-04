package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class MaterialInputFormController {
    @FXML
    private ComboBox<?> clrCmbBx;

    @FXML
    private Text dateTxt;

    @FXML
    private ComboBox<?> orderIdCmbBox;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField qtyTxt;

    @FXML
    private ComboBox<?> typeCmbBx1;

    @FXML
    void addBtnOnAction(ActionEvent event) {

    }

    @FXML
    void cutInBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.CUT_IN,pane);
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void resevedBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.RESEVED_IN,pane);
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }
}
