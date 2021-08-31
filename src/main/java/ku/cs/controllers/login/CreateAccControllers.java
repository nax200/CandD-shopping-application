package ku.cs.controllers.login;

import javafx.fxml.FXML;
import ku.cs.App;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class CreateAccControllers {

    @FXML
    private void switchToLoginPage() throws IOException {
        FXRouter.goTo("login");
    }
}
