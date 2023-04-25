package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.Order;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.tm.BuyerTM;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.model.BuyerModel;
import lk.ijse.project_dkf.model.OrderModel;
import lk.ijse.project_dkf.model.OrderRatioModel;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderRatioController implements Initializable {
    @FXML
    private Button addBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label clothIDTxt;
    @FXML
    private TableColumn<?, ?> idColm;
    @FXML
    private TableColumn<?, ?> clrColm;
    @FXML
    private TableColumn<?, ?> descColm;
    @FXML
    private TableColumn<?, ?> sColm;
    @FXML
    private TableColumn<?, ?> mColm;
    @FXML
    private TableColumn<?, ?> lColm;
    @FXML
    private TableColumn<?, ?> xlColm;
    @FXML
    private TableColumn<?, ?> xxlColm;
    @FXML
    private TextField clrTxt;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField lSizeTxt;
    @FXML
    private TextField mSizeTxt;
    @FXML
    private Text orderIdTxt;
    @FXML
    private TextField sSizeTxt;
    @FXML
    private TextField xlSizeTxt;
    @FXML
    private TextField xxlSizeTxt;
    @FXML
    private TableView<OrderRatioTM> tblOrderRatio;
    public static ObservableList<OrderRatioTM> orderRatioTM=FXCollections.observableArrayList();
    boolean desc,clr,s,m,l,xl,xxl;
    {
        desc=false;
        clr=false;
        s=false;
        m=false;
        l=false;
        xl=false;
        xxl=false;
    }
    @FXML
    void descriptionTxtOnAction(ActionEvent event) {
        clrTxt.requestFocus();
    }
    @FXML
    void clrTxtOnAction(ActionEvent event) {
        sSizeTxt.requestFocus();
    }
    @FXML
    void sSizeTxtOnAction(ActionEvent event) {
        mSizeTxt.requestFocus();
    }
    @FXML
    void mSizeTxtOnAction(ActionEvent event) {
        lSizeTxt.requestFocus();
    }
    @FXML
    void lSizeTxtOnAction(ActionEvent event) {
        xlSizeTxt.requestFocus();
    }
    @FXML
    void xlSizeTxtOnAction(ActionEvent event) {
        xxlSizeTxt.requestFocus();
    }
    @FXML
    void xxlSizeTxtOnAction(ActionEvent event) {
        addBtn.fire();
    }
    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.NEW_ORDER, pane);
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        desc= inputsValidation.isNullTxt(descriptionTxt);
        clr=inputsValidation.isNullTxt(clrTxt);
        s=inputsValidation.isNumberOrNull(sSizeTxt);
        m=inputsValidation.isNumberOrNull(mSizeTxt);
        l=inputsValidation.isNumberOrNull(lSizeTxt);
        xl=inputsValidation.isNumberOrNull(xlSizeTxt);
        xxl=inputsValidation.isNumberOrNull(xxlSizeTxt);

        if (desc && clr && s && m && l && xl && xxl){
            orderRatioTM.add(new OrderRatioTM(
                    clothIDTxt.getText(),
                    clrTxt.getText(),
                    descriptionTxt.getText(),
                    Integer.parseInt(sSizeTxt.getText()),
                    Integer.parseInt(mSizeTxt.getText()),
                    Integer.parseInt(lSizeTxt.getText()),
                    Integer.parseInt(xlSizeTxt.getText()),
                    Integer.parseInt(xxlSizeTxt.getText())
            ));
            tblOrderRatio.setItems(orderRatioTM);

            String[]strings=clothIDTxt.getText().split("Cl-");
            int id= Integer.parseInt(strings[1]);
            id++;

            String num=String.valueOf(id);
            String txt="Cl-"+num;
            clothIDTxt.setText(txt);

            clrTxt.clear();
            descriptionTxt.setText("");
            sSizeTxt.setText("");
            mSizeTxt.setText("");
            lSizeTxt.setText("");
            xlSizeTxt.setText("");
            xxlSizeTxt.setText("");
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        OrderRatioTM selectedItem = tblOrderRatio.getSelectionModel().getSelectedItem();
        String id=selectedItem.getId();
        for (int i = 0; i < orderRatioTM.size(); i++) {
            if (orderRatioTM.get(i).getId().equals(id)){
                orderRatioTM.remove(i);
                break;
            }
        }
    }
    @FXML
    void nxtBtnOnAction(ActionEvent event) throws IOException {
        if (orderRatioTM.size()==0){
            PopUps.popUps(AlertTypes.INFORMATION, "Attention", "Please add order ratio.");
        }else {
            Navigation.navigation(Rout.TRIM_CARD,pane);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        setCellValueFactory();
        if (orderRatioTM.size()==0){
            generateOrderID();
        }else {
            generateOrderIDByArray();
        }
        if (orderRatioTM !=null){
            loadValues();
        }
    }
    private void generateOrderIDByArray() {
        OrderRatioTM orderRatio=orderRatioTM.get(orderRatioTM.size()-1);
        String string=orderRatio.getId();
        String [] ar=string.split("Cl-");
        int id= Integer.parseInt(ar[1]);
        id++;

        String num=String.valueOf(id);
        String txt="Cl-"+num;
        clothIDTxt.setText(txt);
    }
    private void loadValues() {
        tblOrderRatio.setItems(orderRatioTM);
    }
    private void setCellValueFactory() {
        idColm.setCellValueFactory(new PropertyValueFactory<>("id"));
        clrColm.setCellValueFactory(new PropertyValueFactory<>("clr"));
        descColm.setCellValueFactory(new PropertyValueFactory<>("desc"));
        sColm.setCellValueFactory(new PropertyValueFactory<>("s"));
        mColm.setCellValueFactory(new PropertyValueFactory<>("m"));
        lColm.setCellValueFactory(new PropertyValueFactory<>("l"));
        xlColm.setCellValueFactory(new PropertyValueFactory<>("xl"));
        xxlColm.setCellValueFactory(new PropertyValueFactory<>("xxl"));
    }
    private void setValues() {
        orderIdTxt.setText(NewOrderFormController.order.getOrderId());
    }
    private void generateOrderID() {
        try {
            String id = OrderRatioModel.getNextOrderRatioID();
            clothIDTxt.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
