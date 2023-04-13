package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PackingFormController implements Initializable {

    @FXML
    private ComboBox<?> clrCmbBx;

    @FXML
    private TableColumn<?, ?> clrColm;

    @FXML
    private TableColumn<?, ?> dateColm;

    @FXML
    private Text dateTxt;

    @FXML
    private ComboBox<?> orderIdCmbBox;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> qtyColm;

    @FXML
    private TextField qtyTxt;

    @FXML
    private ComboBox<?> sizeCmbBx;

    @FXML
    private TableColumn<?, ?> sizeColm;

    @FXML
    private TableView<?> tblPacking;

    @FXML
    void addBtnOnAction(ActionEvent event) {

    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
