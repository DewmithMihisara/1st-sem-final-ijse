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
import lk.ijse.project_dkf.model.OrderRatioModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderRatioController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Button dnBtn;
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
    private TableView<OrderRatioTM> tblOrderRatio;

    @FXML
    void addBtnOnAction(ActionEvent event) {
        OrderRatio orderRatio=new OrderRatio(
                setOrderId,
                descriptionTxt.getText(),
                clrTxt.getText(),
                Integer.parseInt(sSizeTxt.getText()),
                Integer.parseInt(mSizeTxt.getText()),
                Integer.parseInt(lSizeTxt.getText()),
                Integer.parseInt(xlSizeTxt.getText()),
                Integer.parseInt(xxlSizeTxt.getText())
        );
        try {
            boolean add=OrderRatioModel.addRatio(orderRatio);
            tblOrderRatio.refresh();
            if (add ) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Add")
                        .show();
                descriptionTxt.clear();
                clrTxt.clear();
                sSizeTxt.clear();
                mSizeTxt.clear();
                lSizeTxt.clear();
                xlSizeTxt.clear();
                xxlSizeTxt.clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        OrderRatioTM selectedItem = tblOrderRatio.getSelectionModel().getSelectedItem();
        try {
            boolean delete=OrderRatioModel.delete(selectedItem.getClr(),setOrderId);
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Deleted !")
                        .show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }
    @FXML
    void relodeBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.ORDER_RATIO,pane);
    }
    @FXML
    void doneBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) dnBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        setCellValueFactory();
        loadValues();
    }

    private void loadValues() {
        ObservableList<OrderRatioTM> orderRatioObj = FXCollections.observableArrayList();
        try {
            List<OrderRatio> all = OrderRatioModel.getAll(setOrderId);
            for (OrderRatio orderRatio: all){
                orderRatioObj.add(new OrderRatioTM(
                        orderRatio.getColour(),
                        orderRatio.getDisc(),
                        orderRatio.getSQty(),
                        orderRatio.getMQty(),
                        orderRatio.getLQty(),
                        orderRatio.getXlQty(),
                        orderRatio.getXxlty()
                ));
            }
            tblOrderRatio.setItems(orderRatioObj);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }

    private void setCellValueFactory() {
        clrColm.setCellValueFactory(new PropertyValueFactory<>("clr"));
        descColm.setCellValueFactory(new PropertyValueFactory<>("desc"));
        sColm.setCellValueFactory(new PropertyValueFactory<>("s"));
        mColm.setCellValueFactory(new PropertyValueFactory<>("m"));
        lColm.setCellValueFactory(new PropertyValueFactory<>("l"));
        xlColm.setCellValueFactory(new PropertyValueFactory<>("xl"));
        xxlColm.setCellValueFactory(new PropertyValueFactory<>("xxl"));
    }

    private void setValues() {
        orderIdTxt.setText(setOrderId);
    }


}
