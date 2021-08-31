package ku.cs.controllers.login;

import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import ku.cs.App;

import java.io.IOException;

public class CreditPageControllers {

    @FXML
    private void switchToLoginPage() throws IOException {
        FXRouter.goTo("login");
    }
}
