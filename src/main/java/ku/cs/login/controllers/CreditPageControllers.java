package ku.cs.login.controllers;

import javafx.fxml.FXML;
import ku.cs.App;

import java.io.IOException;

public class CreditPageControllers {

    @FXML
    private void switchToLoginPage() throws IOException {
        App.setRoot("login");
    }
}
