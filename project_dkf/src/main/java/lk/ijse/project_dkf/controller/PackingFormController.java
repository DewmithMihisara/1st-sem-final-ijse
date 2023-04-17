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
import lk.ijse.project_dkf.animation.SetTime;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.animation.defueltText;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.PackingTM;
import lk.ijse.project_dkf.model.*;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PackingFormController implements Initializable {
    @FXML
    private Label timeLbl;
    @FXML
    private TableColumn<?, ?> timeColm;
    @FXML
    private ComboBox<String> clrCmbBx;
    @FXML
    private TableColumn<?, ?> clrColm;
    @FXML
    private TableColumn<?, ?> dateColm;
    @FXML
    private Text dateTxt;
    @FXML
    private ComboBox<String> orderIdCmbBox;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<?, ?> qtyColm;
    @FXML
    private TextField qtyTxt;
    @FXML
    private ComboBox<String> sizeCmbBx;
    @FXML
    private TableColumn<?, ?> sizeColm;
    @FXML
    private TableView<PackingTM> tblPacking;
    boolean clr,size,qty;

    {
        clr=false;
        size=false;
        qty=false;
    }
    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {

        qty=inputsValidation.isNumberOrNull(qtyTxt);
        size= inputsValidation.isNullCmb(sizeCmbBx);
        clr= inputsValidation.isNullCmb(clrCmbBx);

        if (clr && size && qty){
            Pack pack=new Pack(
                    orderIdCmbBox.getSelectionModel().getSelectedItem(),
                    Date.valueOf(dateTxt.getText()),
                    Time.valueOf(timeLbl.getText()),
                    clrCmbBx.getSelectionModel().getSelectedItem(),
                    sizeCmbBx.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(qtyTxt.getText())
            );
            try {
                boolean affectedRows= PackingModel.add(pack);
                if (affectedRows ) {
                    new Alert(Alert.AlertType.CONFIRMATION,
                            "Add!")
                            .show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,
                        "Something is wrong")
                        .show();
                e.printStackTrace();
            }finally {
                loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
                clrCmbBx.setValue(null);
                sizeCmbBx.setValue(null);
                qtyTxt.setText("");
            }
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        PackingTM packingTM = tblPacking.getSelectionModel().getSelectedItem();
        try {
            boolean delete=PackingModel.delete(packingTM, orderIdCmbBox.getSelectionModel().getSelectedItem());
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Deleted !")
                        .show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }finally {
            loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    void idOnAction(ActionEvent event) {
        String id= String.valueOf(orderIdCmbBox.getSelectionModel().getSelectedItem());
        loadValues(id);
        loadClr();
        clrCmbBx.setDisable(false);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadOrderIds();
        loadSize();
        setDate();
        setTime();
    }
    private void loadClr() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = IdModel.loadClr(orderIdCmbBox.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        clrCmbBx.setItems(obList);
    }
    private void setDate() {
        dateTxt.setText(String.valueOf(LocalDate.now()));
    }
    private void loadSize() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String>clr=new ArrayList<>();
        clr.add("S");
        clr.add("M");
        clr.add("L");
        clr.add("XL");
        clr.add("XXl");
        obList.addAll(clr);
        sizeCmbBx.setItems(obList);
    }
    private void loadOrderIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = IdModel.loadOrderIds();
        } catch (SQLException e) {}

        for (String id : ids) {
            obList.add(id);
        }
        orderIdCmbBox.setItems(obList);
    }
    private void loadValues(String id) {
        ObservableList<PackingTM> packingTMS = FXCollections.observableArrayList();
        try {
            List<Pack> all = PackingModel.getAll(id);
            for (Pack pack: all){
                packingTMS.add(new PackingTM(
                        pack.getDate(),
                        pack.getTime(),
                        pack.getClr(),
                        pack.getSize(),
                        pack.getPackQty()
                ));
            }
            tblPacking.setItems(packingTMS);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }
    private void setCellValueFactory() {
        dateColm.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColm.setCellValueFactory(new PropertyValueFactory<>("time"));
        clrColm.setCellValueFactory(new PropertyValueFactory<>("clr"));
        sizeColm.setCellValueFactory(new PropertyValueFactory<>("size"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    void setTime(){
        SetTime.setTime(timeLbl);
    }
}
