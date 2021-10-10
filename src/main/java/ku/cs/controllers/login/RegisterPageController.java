package ku.cs.controllers.login;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.models.user.Register;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class RegisterPageController {

    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;


    public void handleRegisterButton() {
        String name = nameTextField.getText().trim();
        String username = usernameTextField.getText().trim();
        String password = passwordField.getText().trim();
        String cfPassword = confirmPasswordField.getText().trim();

        if ( name.equals("") || username.equals("") || password.equals("") || cfPassword.equals("") ) {
            errorLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วน");
        }
        else if ( !password.equals(cfPassword) ){
            errorLabel.setText("รหัสผ่านไม่ตรงกัน");
            passwordField.clear();
            confirmPasswordField.clear();
        }
        else if ( Register.Register(name,username,password,cfPassword) ) {
            try {
                goToLoginPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            errorLabel.setText("ชื่อบัญชีนี้ถูกใช้แล้ว");
        }

        }


    @FXML
    private void goToLoginPage() throws IOException {
        FXRouter.goTo("login");
    }
}
