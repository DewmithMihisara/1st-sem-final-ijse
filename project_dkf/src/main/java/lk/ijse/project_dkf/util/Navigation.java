package lk.ijse.project_dkf.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static lk.ijse.project_dkf.util.Rout.*;

public class Navigation {
    private static AnchorPane root;
    public static void navigation(Rout rout,AnchorPane root) throws IOException {
        Navigation.root = root;
        Navigation.root.getChildren().clear();
        Stage window = (Stage) Navigation.root.getScene().getWindow();

        switch (rout){
            case LOGIN:
                initUi("logInForm.fxml");
                break;
            case NEW_AC :
                initUi("newAcForm.fxml");
                break;
            case DASHBOARD:
                initUi("dashboardForm.fxml");
                break;
            case USER_SETTINGS:
                initUi("settingForm.fxml");
                break;
            case ORDER:
                initUi("ordrForm.fxml");
                break;
        }
    }

    private static void initUi(String location) throws IOException {
        Navigation.root.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/"+location)));
    }
}


