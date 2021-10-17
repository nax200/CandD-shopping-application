package ku.cs.controllers.admin.customerlist;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminAllUserListController implements Initializable {
    @FXML
    private Circle profileImage;

    @FXML
    private Label username;

    @FXML
    private Label name;

    @FXML
    private Label shopname;

    @FXML
    private Label lastlogin;

    @FXML
    private Label userBlocked;

    @FXML
    private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void setData(User user) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(((Customer) user).getImageFile());
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        profileImage.setFill(new ImagePattern(image));
        name.setText(user.getName());
        username.setText(user.getUsername());
        shopname.setText(user.getShopName());
        lastlogin.setText(user.getLastTimeLoggedInToString());
        if (user.isBlocked()) {
            if (ThemeController.isLightMode) {
                userBlocked.setTextFill(Color.rgb(255, 84, 47)); //ff542f 255 84 47
            } else {
                userBlocked.setTextFill(Color.rgb(255, 144, 120));
            }
            userBlocked.setText(user.getIsUserBlockedToString());
        } else {
            if (ThemeController.isLightMode) {
                userBlocked.setTextFill(Color.rgb(111, 111, 111)); //ff542f 255 84 47
            } else {
                userBlocked.setTextFill(Color.rgb(255, 255, 255));
            }
            userBlocked.setText(user.getIsUserBlockedToString());
        }

//        if(user.isBlocked()) {
//            userBlocked.setTextFill(Color.rgb(255,84,47));
//        }
//        else {
//            userBlocked.setTextFill(Color.rgb(111,111,111));
//        }
//        userBlocked.setText(user.getIsUserBlockedToString());
//    }
    }

}

