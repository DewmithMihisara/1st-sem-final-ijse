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
import lk.ijse.project_dkf.dto.Cut;
import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.CutTM;
import lk.ijse.project_dkf.dto.tm.PackingTM;
import lk.ijse.project_dkf.model.CutModel;
import lk.ijse.project_dkf.model.IdModel;
import lk.ijse.project_dkf.model.OutputModel;
import lk.ijse.project_dkf.model.PackingModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CutInputFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> clrColm;

    @FXML
    private TableView<CutTM> cutTbl;

    @FXML
    private TableColumn<?, ?> dateColm;
    @FXML
    private TableColumn<?, ?> oIdColm;
    @FXML
    private TableColumn<?, ?> qtyColm;
    @FXML
    private TableColumn<?, ?> sizeColm;

    @FXML
    private TableColumn<?, ?> typeColm;
    @FXML
    private ComboBox<String> clrCmbBx;

    @FXML
    private Text dateTxt;

    @FXML
    private ComboBox<String> orderIdCmbBox;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField qtyTxt;

    @FXML
    private ComboBox<String> sizeCmbBx;

    @FXML
    private TextField typeTxt;

    @FXML
    void addBtnOnAction(ActionEvent event) {
        Cut cut = new Cut(
                orderIdCmbBox.getSelectionModel().getSelectedItem(),
                Date.valueOf(dateTxt.getText()),
                Integer.parseInt(qtyTxt.getText()),
                typeTxt.getText(),
                sizeCmbBx.getSelectionModel().getSelectedItem(),
                clrCmbBx.getSelectionModel().getSelectedItem()
        );
        try {
            boolean affectedRows = CutModel.add(cut);
            if (affectedRows) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Add!")
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
    void deleteBtnOnAction(ActionEvent event) {
        CutTM cutTM = cutTbl.getSelectionModel().getSelectedItem();
        try {
            boolean delete = CutModel.delete(cutTM, orderIdCmbBox.getSelectionModel().getSelectedItem());
            if (delete) {
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
    void materialBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.MATERIAL_IN, pane);
    }

    @FXML
    void resevedBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.RESEVED_IN, pane);
    }

    @FXML
    void relodeBtnOnAction(ActionEvent event) {
        loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    void orderIdOnAction(ActionEvent event) {
        loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
        loadClr();
        clrCmbBx.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSize();
        loadOrderIds();
        setOrderDate();
        setCellValueFactory();
    }

    private void loadSize() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> clr = new ArrayList<>();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        orderIdCmbBox.setItems(obList);
    }

    private void loadValues(String id) {
        ObservableList<CutTM> cutTMS = FXCollections.observableArrayList();
        try {
            List<Cut> all = CutModel.getAll(id);
            for (Cut cut : all) {
                cutTMS.add(new CutTM(
                        cut.getDate(),
                        cut.getCutID(),
                        cut.getClr(),
                        cut.getSize(),
                        cut.getType(),
                        cut.getCutQty()
                ));
            }
            cutTbl.setItems(cutTMS);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
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

    private void setCellValueFactory() {
        dateColm.setCellValueFactory(new PropertyValueFactory<>("date"));
        oIdColm.setCellValueFactory(new PropertyValueFactory<>("oID"));
        clrColm.setCellValueFactory(new PropertyValueFactory<>("clr"));
        sizeColm.setCellValueFactory(new PropertyValueFactory<>("size"));
        typeColm.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void setOrderDate() {
        dateTxt.setText(String.valueOf(LocalDate.now()));
    }
}
