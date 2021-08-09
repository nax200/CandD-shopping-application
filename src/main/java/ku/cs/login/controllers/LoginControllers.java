package ku.cs.login.controllers;

import javafx.fxml.FXML;
import ku.cs.App;

import java.io.IOException;

public class LoginControllers {

    @FXML
    private void switchToCreateAccPage() throws IOException {
        App.setRoot("create-acc");
    }

    @FXML
    private void switchToCreditPage() throws IOException {
        App.setRoot("credit");
    }
}
