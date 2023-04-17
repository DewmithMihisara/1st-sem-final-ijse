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
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.dto.tm.OutputTM;
import lk.ijse.project_dkf.dto.tm.PackingTM;
import lk.ijse.project_dkf.model.*;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Getter
public class OutputFormController implements Initializable {
    @FXML
    private Label timeLbl;
    @FXML
    private AnchorPane root;
    @FXML
    private TableColumn<?, ?> clrCol;

    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> sizeCol;
    @FXML
    private TableColumn<?, ?> qtyCol;
    @FXML
    private TableView<OutputTM> outTbl;
    @FXML
    private ComboBox<String> clrCmbBx;

    @FXML
    private Text dateTxt;

    @FXML
    private ComboBox<String> orderIdCmbBox;

    @FXML
    private TextField qtyTxt;

    @FXML
    private ComboBox<String> sizeCmbBx;
    boolean clr,size,qty;

    {
        clr=false;
        size=false;
        qty=false;
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        clr= inputsValidation.isNullCmb(clrCmbBx);
        size=inputsValidation.isNullCmb(sizeCmbBx);
        qty=inputsValidation.isNumberOrNull(qtyTxt);

        if (clr && size && qty){
            Output output = new Output(
                    orderIdCmbBox.getSelectionModel().getSelectedItem(),
                    Date.valueOf(dateTxt.getText()),
                    Time.valueOf(timeLbl.getText()),
                    clrCmbBx.getSelectionModel().getSelectedItem(),
                    sizeCmbBx.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(qtyTxt.getText())
            );
            try {
                boolean affectedRows = OutputModel.add(output);
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
                clrCmbBx.setValue(null);
                sizeCmbBx.setValue(null);
                qtyTxt.setText("");
            }
        }

    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        OutputTM output=outTbl.getSelectionModel().getSelectedItem();
        try {
            boolean delete = OutputModel.delete(output, orderIdCmbBox.getSelectionModel().getSelectedItem());
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
    void orderIdOnAction(ActionEvent event) {
        loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
        loadClr();
        clrCmbBx.setDisable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderDate();
        setCellValueFactory();
        loadOrderIds();
        loadSize();
        setTime();
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
        ObservableList<OutputTM> outputTMS = FXCollections.observableArrayList();
        try {
            List<Output> all = OutputModel.getAll(id);
            for (Output output: all){
                outputTMS.add(new OutputTM(
                        output.getDate(),
                        output.getTime(),
                        output.getClr(),
                        output.getSize(),
                        output.getOut()
                ));
            }
            outTbl.setItems(outputTMS);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Something is wrong")
                    .show();
        }
    }
    private void setCellValueFactory() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        clrCol.setCellValueFactory(new PropertyValueFactory<>("clr"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("out"));
    }
    void setTime(){
        SetTime.setTime(timeLbl);
    }
}
