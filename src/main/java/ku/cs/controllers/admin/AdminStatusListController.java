package ku.cs.controllers.admin;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.admin.AdminUser;
import ku.cs.models.admin.AdminUserReport;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminStatusListController implements Initializable {
    @FXML
    private Circle profileImage;

    @FXML
    private Label username;

    @FXML
    private Label moreCause;

    @FXML
    private Label countTrytoLogin;

    @FXML
    private ComboBox<String> userStatus;

    private Customer customer;
    public void setData(User user){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(((Customer)user).getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        profileImage.setFill(new ImagePattern(image));
        username.setText(user.getUsername());
        moreCause.setText("");
        countTrytoLogin.setText("");
        userStatus.setValue(((Customer)user).getIsUserBlockedToString());
        customer = (Customer)user;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        userStatus.getItems().addAll("ปกติ","ถูกจำกัด");
        userStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(userStatus.getValue().equals("ปกติ")){
                    (userList.searchUsername(customer.getUsername())).setStatus(false);
                }
                else {
                    (userList.searchUsername(customer.getUsername())).setStatus(true);
                }
                dataSource.writeData(userList);
            }
        });

    }
}

