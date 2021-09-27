package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.admin.AdminUser;
import ku.cs.models.admin.AdminUserReport;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminStatusListController implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label username;

    @FXML
    private Label moreCause;

    @FXML
    private Label countTrytoLogin;

    @FXML
    private ComboBox<String> userStatus;

    public void setData(AdminUserReport user){
        Image imageProfile = new Image(getClass().getResourceAsStream(user.getImgSrc()));
        img.setImage(imageProfile);
        username.setText(user.getUsername());
        moreCause.setText(user.getReportType());
        countTrytoLogin.setText(""+user.getTrytoLoginCount());
        userStatus.setValue("Normal");
        userStatus.getItems().addAll("Normal","Banned");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

