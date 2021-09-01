package ku.cs.controllers.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import ku.cs.models.login.Register;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class RegisterControllers {

    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;


    public void handleRegisterButton() {
        if(nameTextField.getText().equals("") || usernameTextField.getText().equals("") || passwordField.getText().equals("") || confirmPasswordField.getText().equals("")){
            errorLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วน");
        }
        else if(!Register.checkUsername(usernameTextField.getText())){
            errorLabel.setText("ชื่อบัญชีนี้ถูกใช้แล้ว");
        }else {
            if(Register.Register(nameTextField.getText(), usernameTextField.getText(), passwordField.getText(), confirmPasswordField.getText())) {
                nameTextField.clear();
                usernameTextField.clear();
                passwordField.clear();
                confirmPasswordField.clear();
                errorLabel.setText("");
            }
            else {
                errorLabel.setText("รหัสผ่านไม่ตรงกัน");
                passwordField.clear();
                confirmPasswordField.clear();
            }
        }
    }

    @FXML
    private void goToLoginPage() throws IOException {
        FXRouter.goTo("login");
    }
}
