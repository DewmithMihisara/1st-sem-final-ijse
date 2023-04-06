package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.model.NewOrderModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.NewWindowNavigation;
import lk.ijse.project_dkf.util.Rout;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
@Getter
public class NewOrderFormController implements Initializable {
    @FXML
    private TextField orderIdTxt;
    @FXML
    private ComboBox<String> companyCmbBox;
    @FXML
    private TextField companyNameTxt;

    @FXML
    private TextField daylyOutTxt;

    @FXML
    private DatePicker dedlineDate;

    @FXML
    private Text orderDateTxt;
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
    void orderRatioBtnOnAction(ActionEvent event) throws IOException {
        if(orderIdTxt.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,
                    "Fill Order Id")
                    .show();
        }else{
            OrderRatioController.setOrderId=orderIdTxt.getText();
            NewWindowNavigation.windowNavi(Rout.ORDER_RATIO);
        }

    }

    @FXML
    void placeOrderOnAction(ActionEvent event) {

    }

    @FXML
    void trimCardBtnOnAction(ActionEvent event) throws IOException {
        if(orderIdTxt.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,
                    "Fill Order Id")
                    .show();
        }else{
            TrimCardFormController.setOrderId=orderIdTxt.getText();
            NewWindowNavigation.windowNavi(Rout.TRIM_CARD);
        }

    }

    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.ORDER, root);
    }
    @FXML
    void newBuyerBtnOnAction(ActionEvent event) throws IOException {
        NewWindowNavigation.windowNavi(Rout.BUYER);
    }
    @FXML
    void companyCmbOnAction(ActionEvent event) {
        String id = (String) companyCmbBox.getValue();

        Buyer buyer = null;
        try {
            buyer = NewOrderModel.searchById(id);
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING,"Sql Error!").show();
        }
        companyNameTxt.setText(buyer.getBuyerName());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCompanyIds();
        setOrderDate();
    }

    private void setOrderDate() {
        orderDateTxt.setText(String.valueOf(LocalDate.now()));
    }
    private void loadCompanyIds(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = NewOrderModel.loadIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        companyCmbBox.setItems(obList);
    }
}
