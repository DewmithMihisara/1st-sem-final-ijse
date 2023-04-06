package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.dto.Order;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.model.BuyerModel;
import lk.ijse.project_dkf.model.OrderRatioModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderRatioController implements Initializable {
    public static String setOrderId;
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
    void addBtnOnAction(ActionEvent event) {
        OrderRatio orderRatio=new OrderRatio(orderIdTxt.getText(), descriptionTxt.getText(), clrTxt.getText(), Integer.parseInt(sSizeTxt.getText()), Integer.parseInt(mSizeTxt.getText()), Integer.parseInt(lSizeTxt.getText()), Integer.parseInt(xlSizeTxt.getText()), Integer.parseInt(xxlSizeTxt.getText()));
        try {
            boolean affectedRows= OrderRatioModel.addOrderRatio(orderRatio);
            if (affectedRows ) {
                descriptionTxt.clear();
                clrTxt.clear();
                sSizeTxt.clear();
                mSizeTxt.clear();
                lSizeTxt.clear();
                xlSizeTxt.clear();
                xxlSizeTxt.clear();
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Order Add!")
                        .show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
    }

    private void setValues() {
        orderIdTxt.setText(setOrderId);
    }


}
