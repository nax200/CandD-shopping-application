package ku.cs.controllers.login;

import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ku.cs.App;
import ku.cs.controllers.ThemeController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreditPageController implements Initializable {
    @FXML private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    @FXML
    private void goToLoginPage() throws IOException {
        FXRouter.goTo("login");
    }
}
