package ku.cs.controllers.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import java.io.IOException;

public class LoginPageControllers {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleLoginButton() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();

        try {
            if ( username.equals("") || password.equals("") ){
                messageLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วน");
            }
            else if ( userList.verifyLogin(username,password) ) {

                userList.setLastLogInTime(userList.searchUsername(username));
                dataSource.writeData(userList);

                if ( username.equals("admin") ) {
                    FXRouter.goTo("admin-user-view");
                } else {
                    if( ( (Customer) userList.searchUsername(username) ).isBlocked() ){


                        // รอเพิ่มโค้ดจำนวนครั้งในความพยายามเข้าสู่ระบบ

                        messageLabel.setText("ขออภัย! บัญชีของท่านถูกระงับการใช้งาน");
                    } else {
                        FXRouter.goTo("market-place");
                    }
                }
            } else {
                messageLabel.setText("ชื่อบัญชีหรือรหัสผ่านไม่ถูกต้อง");
            }

        } catch (Exception e) {
            System.err.println("เข้าหน้าหลังจากล็อกอินไม่ได้");
        }

    }

    @FXML
    private void goToRegisterPage() throws IOException {
        FXRouter.goTo("register");
    }

    @FXML
    private void goToCreditPage() throws IOException {
        FXRouter.goTo("credit");
    }
}
