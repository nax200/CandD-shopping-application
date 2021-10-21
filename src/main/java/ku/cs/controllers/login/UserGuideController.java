package ku.cs.controllers.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserGuideController implements Initializable {
    @FXML private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }
    @FXML
    private void goToCreditPage() {
        try {
            com.github.saacsos.FXRouter.goTo("credit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า credit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
