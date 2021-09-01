package ku.cs.controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.App;
import com.github.saacsos.FXRouter;
import ku.cs.models.login.Login;

import java.io.IOException;

public class LoginControllers {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label wrongUsernameOrPasswordLabel;

    @FXML
    public void initialize() {

    }

    @FXML
    private void handleLoginButton() {
        try {
            if( Login.login( usernameTextField.getText(),passwordField.getText()) ) {
                wrongUsernameOrPasswordLabel.setText("ชื่อบัญชีหรือรหัสผ่านไม่ถูกต้อง");
            }else {
                FXRouter.goTo("marketPlace");
            }
        } catch (Exception e) {
            System.err.println("ผิดพลาด");
        }

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
