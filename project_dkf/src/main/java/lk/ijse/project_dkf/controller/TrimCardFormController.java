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
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.TrimCard;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.dto.tm.TrimCardTM;
import lk.ijse.project_dkf.model.OrderRatioModel;
import lk.ijse.project_dkf.model.TrimCardModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TrimCardFormController implements Initializable {
    public static String setOrderId;
    @FXML
    private TableColumn<?, ?> clrColm;

    @FXML
    private TextField clrTxt;

    @FXML
    private Button dnBtn;

    @FXML
    private TextField materialTxt;

    @FXML
    private Text orderIdTxt;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> qtyColm;

    @FXML
    private TextField reqTtlTxt;

    @FXML
    private TableView<TrimCardTM> tblTrimCard;

    @FXML
    private TableColumn<?, ?> typeColm;

    @FXML
    void addBtnOnAction(ActionEvent event) {
        TrimCard trimCard=new TrimCard(setOrderId,materialTxt.getText(),clrTxt.getText(),Integer.parseInt(reqTtlTxt.getText()));
        try {
            boolean add= TrimCardModel.addTrimCard(trimCard);
            if (add){
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Add")
                        .show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Somthing Wrong")
                    .show();
        }
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        TrimCardTM selectedItem = tblTrimCard.getSelectionModel().getSelectedItem();
        try {
            boolean delete=TrimCardModel.delete(selectedItem,setOrderId);
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Add")
                        .show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Somthing Wrong")
                    .show();
        }
    }

    @FXML
    void doneBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) dnBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void relodeBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.TRIM_CARD,pane);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        setCellValueFactory();
        loadValues();
    }

    private void loadValues() {
        ObservableList<TrimCardTM> trimCardObj = FXCollections.observableArrayList();
        try {
            List<TrimCard> all = TrimCardModel.getAll(setOrderId);
            for (TrimCard trimCard: all){
                trimCardObj.add(new TrimCardTM(
                        trimCard.getTyp(),
                        trimCard.getClr(),
                        trimCard.getQty()
                ));
            }
            tblTrimCard.setItems(trimCardObj);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }

    private void setCellValueFactory() {
        clrColm.setCellValueFactory(new PropertyValueFactory<>("clr"));
        typeColm.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void setValues() {
        orderIdTxt.setText(setOrderId);
    }
}
