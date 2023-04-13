package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.tm.BuyerTM;
import lk.ijse.project_dkf.model.BuyerModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BuyerFormController implements Initializable {
    @FXML
    private AnchorPane midleStage;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableView tblBuyer;
    @FXML
    private TextField buyerIdTxt;
    @FXML
    private TextField BuyerCnTxt;

    @FXML
    private TextField buyerAddTxt;

    @FXML
    private TextField buyerNameTxt;

    @FXML
    private Text idTxt;
    @FXML
    void relodeBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.BUYER,midleStage);
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        Buyer buyer=new Buyer(buyerIdTxt.getText(),buyerNameTxt.getText(),BuyerCnTxt.getText(),buyerAddTxt.getText());
        try {
            boolean affectedRows= BuyerModel.addBuyer(buyer);
            tblBuyer.refresh();
            if (affectedRows ) {
                buyerIdTxt.clear();
                buyerNameTxt.clear();
                buyerAddTxt.clear();
                BuyerCnTxt.clear();

                new Alert(Alert.AlertType.CONFIRMATION,
                        "Buyer Add!")
                        .show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }
    @FXML
    void idSearchOnAction(ActionEvent event) {
        Buyer buyer=new Buyer();
        buyer.setBuyerId(buyerIdTxt.getText());

        try {
            Buyer searchBuyer=BuyerModel.search(buyer);
            if (!(searchBuyer==null)){
                buyerIdTxt.setText(searchBuyer.getBuyerId());
                buyerNameTxt.setText(searchBuyer.getBuyerName());
                buyerAddTxt.setText(searchBuyer.getBuyerAddress());
                BuyerCnTxt.setText(searchBuyer.getBuyerCn());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"Something happened :(").show();
            e.printStackTrace();
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        Buyer buyer=new Buyer();
        buyer.setBuyerId(buyerIdTxt.getText());

        try {
            boolean delete=BuyerModel.delete(buyer);
            if(delete){
                new Alert(Alert.AlertType.CONFIRMATION,"Buyer Deleted ! :)").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"Something happened :(").show();
            e.printStackTrace();
        }
    }
    @FXML
    void updateBtnOnAction(ActionEvent event) {
        Buyer buyer=new Buyer(buyerIdTxt.getText(),buyerNameTxt.getText(),BuyerCnTxt.getText(),buyerAddTxt.getText());
//        Buyer buyer = Buyer.builder()
//                .buyerId(buyerIdTxt.getText())
//                .buyerName(buyerNameTxt.getText())
//                .build();

        try {
            boolean update=BuyerModel.update(buyer);
            if(update){
                new Alert(Alert.AlertType.CONFIRMATION,"Buyer updated ! :)").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"something happened ! :(").show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCelValueFactory();
        ObservableList<BuyerTM> object = FXCollections.observableArrayList();
        try {
            List<Buyer> all = BuyerModel.getAll();
            for (Buyer buyer: all){
                object.add(new BuyerTM(
                   buyer.getBuyerId(),
                   buyer.getBuyerName(),
                   buyer.getBuyerCn(),
                   buyer.getBuyerAddress()
                ));
            }
            tblBuyer.setItems(object);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }
    private void setCelValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("cn"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}
