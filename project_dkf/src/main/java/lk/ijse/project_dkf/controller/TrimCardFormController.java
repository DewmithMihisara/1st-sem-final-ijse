package lk.ijse.project_dkf.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TrimCardFormController implements Initializable {
    public static String setOrderId;
    @FXML
    private TextField clrTxt;

    @FXML
    private TextField materialTxt;

    @FXML
    private Text orderIdTxt;

    @FXML
    private TextField reqTtlTxt;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
    }

    private void setValues() {
        orderIdTxt.setText(setOrderId);
    }
}
