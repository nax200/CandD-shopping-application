package ku.cs.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import ku.cs.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        com.github.saacsos.FXRouter.goTo("primary");
    }
}