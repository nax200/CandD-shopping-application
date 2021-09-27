package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.admin.AdminUser;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUserListController implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label username;

    @FXML
    private Label shopname;

    @FXML
    private Label lastlogin;

    @FXML
    private Label userBlocked;

    public void setData(User user){
//        Image imgProfile = new Image(getClass().getResourceAsStream(user.getImageFilePath()));
//        img.setImage(imgProfile);
        username.setText(user.getUsername());
        shopname.setText(user.getShopName());
        lastlogin.setText(user.getLastTimeLoggedInToString());
        userBlocked.setText(user.getIsUserBlockedToString());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

