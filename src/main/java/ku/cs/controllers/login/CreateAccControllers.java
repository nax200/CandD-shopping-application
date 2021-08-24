package ku.cs.controllers.login;

import javafx.fxml.FXML;
import ku.cs.App;

import java.io.IOException;

public class CreateAccControllers {

    @FXML
    private void switchToLoginPage() throws IOException {
        App.setRoot("login");
    }
}
