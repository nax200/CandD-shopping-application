package ku.cs.controllers.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.login.Register;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class CreateAccControllers {

    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label alreadyUsedUsernameLabel;
    @FXML private Label wrongPasswordLabel;


    public void handleRegisterButton() {
        if(!Register.checkUsername(usernameTextField.getText())){
            alreadyUsedUsernameLabel.setText("ชื่อบัญชีนี้ถูกใช้แล้ว");
        }else {
            if(Register.Register(nameTextField.getText(), usernameTextField.getText(), passwordField.getText(), confirmPasswordField.getText())) {
                nameTextField.clear();
                usernameTextField.clear();
                passwordField.clear();
                confirmPasswordField.clear();
                alreadyUsedUsernameLabel.setText("");
                wrongPasswordLabel.setText("");
            }
            else {
                wrongPasswordLabel.setText("รหัสผ่านไม่ตรงกัน");
                passwordField.clear();
                confirmPasswordField.clear();
            }
        }
    }

    @FXML
    private void switchToLoginPage() throws IOException {
        FXRouter.goTo("login");
    }
}
