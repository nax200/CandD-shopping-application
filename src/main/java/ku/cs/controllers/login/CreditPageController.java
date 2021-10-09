package ku.cs.controllers.login;

import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import ku.cs.App;

import java.io.IOException;

public class CreditPageController {

    @FXML
    private void goToLoginPage() throws IOException {
        FXRouter.goTo("login");
    }
}
