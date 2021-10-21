package ku.cs.controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreditController implements Initializable {
    @FXML private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    @FXML
    private void goToLoginPage() {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToUserGuidePage(ActionEvent event) {
        try {
            FXRouter.goTo("user-guide");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-guide ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
