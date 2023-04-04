package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.NewWindowNavigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;

public class NewOrderFormController {
    @FXML
    private TextField companyIdTxt;

    @FXML
    private TextField companyNameTxt;

    @FXML
    private TextField daylyOutTxt;

    @FXML
    private DatePicker dedlineDate;

    @FXML
    private Text orderDateTxt;

    @FXML
    private Text orderIdTxt;

    @FXML
    private TextField paymentTermTxt;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField ttlQtyTxt;

    @FXML
    void dedlineBtnOnAction(ActionEvent event) {

    }

    @FXML
    void fabricScotchBtnOnAction(ActionEvent event) {

    }

    @FXML
    void orderRatioBtnOnAction(ActionEvent event) {

    }

    @FXML
    void placeOrderOnAction(ActionEvent event) {

    }

    @FXML
    void trimCardBtnOnAction(ActionEvent event) {

    }

    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.ORDER, root);
    }
    @FXML
    void newBuyerBtnOnAction(ActionEvent event) throws IOException {
        NewWindowNavigation.windowNavi(Rout.BUYER);
    }
}
