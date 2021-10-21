package ku.cs.controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import ku.cs.models.user.Customer;
import ku.cs.models.user.LoginCustomer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    @FXML private AnchorPane parent;
    @FXML private ImageView logoImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        if (!ThemeController.isLightMode){
            Image image = new Image("/images/assets/title-bar/logo.png");
            logoImg.setImage(image);
        }
    }

    @FXML
    private void handleLoginButton() {
        String username = usernameTextField.getText().trim().toLowerCase(Locale.ROOT);
        String password = passwordField.getText();

        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();

        try {
            if ( username.equals("") || password.equals("") ){
                messageLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วน");
            }
            else if ( userList.verifyLogin(username,password) ) {
                User user = userList.searchUsername(username);
                userList.setLastLogInTime(user);
                dataSource.writeData(userList);
                if ( username.equals("admin") ) {
                    FXRouter.goTo("admin-user-view",user);
                } else {
                    if( ((Customer) user).isBlocked() ){
                        ((Customer) user).increaseLoginAttempt();
                        dataSource.writeData(userList);
                        messageLabel.setText("ขออภัย! บัญชีของท่านถูกระงับการใช้งาน");
                    } else {
                        LoginCustomer customer = new LoginCustomer(((Customer) user));
                        dataSource.writeData(userList);
                        FXRouter.goTo("market-place");
                    }
                }
            } else {
                messageLabel.setText("ชื่อบัญชีหรือรหัสผ่านไม่ถูกต้อง");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("เข้าหน้าหลังจากล็อกอินไม่ได้");
        }

    }

    @FXML
    private void goToRegisterPage() {
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private void goToCreditPage() {
        try {
            FXRouter.goTo("credit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า credit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private void switchMode(ActionEvent e){
        ThemeController.switchMode(parent);
    }
}
