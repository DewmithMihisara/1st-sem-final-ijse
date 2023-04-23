package lk.ijse.project_dkf.controller;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.animation.defueltText;
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.tm.BuyerTM;
import lk.ijse.project_dkf.dto.tm.CutTM;
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
    private Label buyerIdTxt;
    @FXML
    private TextField BuyerCnTxt;
    @FXML
    private TextField buyerAddTxt;
    @FXML
    private TextField buyerNameTxt;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button addBtn;
    boolean name, address, cuntact;

    {
        name = false;
        address = false;
        cuntact = false;
    }
    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {
        name = buyerName();
        address = address();
        cuntact = contact();

        if (name && address && cuntact) {
            Buyer buyer = new Buyer(buyerIdTxt.getText(), buyerNameTxt.getText(), BuyerCnTxt.getText(), buyerAddTxt.getText());
            try {
                boolean affectedRows = BuyerModel.addBuyer(buyer);
                tblBuyer.refresh();
                if (affectedRows) {
                    generateOrderID();
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
            } finally {
                Navigation.navigation(Rout.BUYER, midleStage);
            }
        }

    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) throws IOException {
        BuyerTM buyer = (BuyerTM) tblBuyer.getSelectionModel().getSelectedItem();
        try {
            boolean delete = BuyerModel.delete(buyer);
            if (delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Buyer Deleted ! :)").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Something happened :(").show();
            e.printStackTrace();
        } finally {
            Navigation.navigation(Rout.BUYER,midleStage);
        }
    }
    @FXML
    void updateBtnOnAction(ActionEvent event) throws IOException {
        BuyerTM tm = (BuyerTM) tblBuyer.getSelectionModel().getSelectedItem();

        if (btnUpdate.getText().equals("Select")) {
            buyerIdTxt.setText(tm.getId());
            buyerAddTxt.setText(tm.getAddress());
            buyerNameTxt.setText(tm.getName());
            BuyerCnTxt.setText(tm.getCn());

            btnUpdate.setText("Update");
            addBtn.setDisable(true);

        } else if (btnUpdate.getText().equals("Update")) {
            name = buyerName();
            address = address();
            cuntact = contact();

            if (name && address && cuntact) {
                Buyer buyer = Buyer.builder()
                        .buyerId(buyerIdTxt.getText())
                        .buyerName(buyerNameTxt.getText())
                        .buyerCn(BuyerCnTxt.getText())
                        .buyerAddress(buyerAddTxt.getText())
                        .build();

                try {
                    boolean update = BuyerModel.update(buyer);
                    if (update) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Buyer updated ! :)").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.WARNING, "something happened ! :(").show();
                    e.printStackTrace();
                } finally {
                    btnUpdate.setText("Select");
                    addBtn.setDisable(false);
                    Navigation.navigation(Rout.BUYER, midleStage);
                }
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCelValueFactory();
        setValues();
        generateOrderID();
    }
    private void generateOrderID() {
        try {
            String id = BuyerModel.getNextOrderID();
            buyerIdTxt.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void setValues() {
        ObservableList<BuyerTM> object = FXCollections.observableArrayList();
        try {
            List<Buyer> all = BuyerModel.getAll();
            for (Buyer buyer : all) {
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
    private void setCelValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("cn"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    boolean buyerName() {
        if (buyerNameTxt.getText().matches("^$")) {
            ShakeTextAnimation.ShakeText(buyerNameTxt);
        } else {
            defueltText.Defuelt(buyerNameTxt);
            return true;
        }
        return false;
    }
    boolean address() {
        if (buyerAddTxt.getText().matches("^$")) {
            ShakeTextAnimation.ShakeText(buyerAddTxt);
        } else {
            defueltText.Defuelt(buyerAddTxt);
            return true;
        }
        return false;
    }
    boolean contact() {
        if (BuyerCnTxt.getText().matches("^$")) {
            ShakeTextAnimation.ShakeText(BuyerCnTxt);
        }
        if (BuyerCnTxt.getText().matches("^(\\d+)$")) {
            defueltText.Defuelt(BuyerCnTxt);
            return true;
        } else {
            ShakeTextAnimation.ShakeText(BuyerCnTxt);
        }
        return false;
    }
}
