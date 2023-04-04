package lk.ijse.project_dkf.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NewWindowNavigation {
    public static void windowNavi(Rout rout) throws IOException {
        switch (rout) {
            case BUYER -> initUi("buyerForm.fxml");
        }
    }
    private static void initUi(String location) throws IOException {
        Parent anchorPane = FXMLLoader.load(Objects.requireNonNull(NewWindowNavigation.class.getResource("/view/" + location)));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Customer Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}