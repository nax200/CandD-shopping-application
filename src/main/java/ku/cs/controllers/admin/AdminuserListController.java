package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.admin.AdminUser;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminuserListController implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label username;

    @FXML
    private Label shopname;

    @FXML
    private Label lastlogin;

    @FXML
    private ChoiceBox<?> statususer;

    public void setData(AdminUser user){
        Image imgProfile = new Image(getClass().getResourceAsStream(user.getImgSrc()));
        img.setImage(imgProfile);
        username.setText(user.getUsername());
        shopname.setText(user.getShopname());
        lastlogin.setText(user.getLastlogin());
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

