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
import lk.ijse.project_dkf.dto.Material;
import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.dto.tm.CutTM;
import lk.ijse.project_dkf.model.CutModel;
import lk.ijse.project_dkf.model.IdModel;
import lk.ijse.project_dkf.model.MaterialModel;
import lk.ijse.project_dkf.model.OutputModel;
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

public class MaterialInputFormController implements Initializable {
    @FXML
    private TableView<Material> tblMetarial;
    @FXML
    private TableColumn<?, ?> dateColm;
    @FXML
    private TableColumn<?, ?> orderColm;
    @FXML
    private TableColumn<?, ?> qtyColm;
    @FXML
    private TableColumn<?, ?> typeColm;
    @FXML
    private Text dateTxt;

    @FXML
    private ComboBox<String> orderIdCmbBox;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField qtyTxt;

    @FXML
    private ComboBox<String> typeCmbBx1;

    @FXML
    void addBtnOnAction(ActionEvent event) {
        Material material = new Material(
                orderIdCmbBox.getSelectionModel().getSelectedItem(),
                typeCmbBx1.getSelectionModel().getSelectedItem(),
                Integer.parseInt(qtyTxt.getText()),
                Date.valueOf(dateTxt.getText())
        );
        try {
            boolean affectedRows = MaterialModel.add(material);
            if (affectedRows) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Add!")
                        .show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }

    @FXML
    void cutInBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.CUT_IN,pane);
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        Material material = tblMetarial.getSelectionModel().getSelectedItem();
        try {
            boolean delete = MaterialModel.delete(material);
            if (delete) {
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
    void resevedBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.RESEVED_IN,pane);
    }
    @FXML
    void relodeBtnOnAction(ActionEvent event) {
        loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
    }
    @FXML
    void orderIdOnAction(ActionEvent event) {
        loadType();
        loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderIds();
        setOrderDate();
        setCellValueFactory();
    }
    private void loadType() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> types = null;
        List<String> clrs=null;
        try {
            types = IdModel.loadType(orderIdCmbBox.getSelectionModel().getSelectedItem());
            clrs = IdModel.loadClr(orderIdCmbBox.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String type : types) {
            for (String clr : clrs){
                obList.add(type+" - "+clr);
            }
        }
        typeCmbBx1.setItems(obList);
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

    private void setOrderDate() {
        dateTxt.setText(String.valueOf(LocalDate.now()));
    }
    private void loadValues(String id) {
        ObservableList<Material> materials = FXCollections.observableArrayList();
        try {
            List<Material> all = MaterialModel.getAll(id);
            for (Material material : all) {
                materials.add(new Material(
                        material.getId(),
                        material.getType(),
                        material.getQty(),
                        material.getDate()
                ));
            }
            tblMetarial.setItems(materials);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }
    private void setCellValueFactory() {
        dateColm.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderColm.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColm.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
}
