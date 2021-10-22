package ku.cs.controllers.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import com.github.saacsos.FXRouter;
import ku.cs.models.user.UserList;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;
    @FXML private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void handleRegisterButton() {
        String name = nameTextField.getText().trim();
        String username = usernameTextField.getText().trim().toLowerCase(Locale.ROOT);
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
        else if (UserList.register(name,username,password) ) {
            goToLoginPage();
        }else{
            errorLabel.setText("ชื่อบัญชีนี้ถูกใช้แล้ว");
        }
    }

    @FXML
    private void goToLoginPage() {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
