package ku.cs.models.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.user.User;

public class Login {

    public static boolean login(String username, String password){
        User user = new User();
        if(user.verifyLogin(username,password)){
            return false;
        }
        return true;
    }
}
