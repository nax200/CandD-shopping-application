package ku.cs.controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.App;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class LoginControllers {

    @FXML
    public void initialize() {

    }

    @FXML
    private void switchToCreateAccPage() throws IOException {
        FXRouter.goTo("create-acc");
    }

    @FXML
    private void switchToCreditPage() throws IOException {
        FXRouter.goTo("credit");
    }
}
