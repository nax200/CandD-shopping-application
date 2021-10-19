package ku.cs.controllers.admin.blocklist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ku.cs.controllers.ThemeController;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AdminCustomerStatusController implements Initializable {
    @FXML private VBox userStatusList;
    @FXML private AnchorPane parent;
    @FXML private ImageView switchModeImgBtn;

    private User admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        if (ThemeController.isLightMode){
            Image img = new Image("/images/assets/title-bar/moon.png");
            switchModeImgBtn.setImage(img);
        } else {
            Image img = new Image("/images/assets/title-bar/sun.png");
            switchModeImgBtn.setImage(img);
        }
        admin = (User) com.github.saacsos.FXRouter.getData();
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        Comparator<User> userComparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getLastTimeLoggedIn().isBefore(o2.getLastTimeLoggedIn()) ) return 1;
                if(o2.getLastTimeLoggedIn().isBefore(o1.getLastTimeLoggedIn())) return -1;
                return 0;
            }
        };
        ConditionFilterer<User> filterer = new ConditionFilterer<User>() {
            @Override
            public boolean match(User user) {
                return user.isBlocked();
            }
        };
        userList.sortTime(userComparator);
        ArrayList<User> userBlocked = userList.filter(filterer);
        for(int i = 0;i<userBlocked.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/blocklist/admin-status-list.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                AdminCustomerStatusListController adminstatusListControllerCustomer = fxmlLoader.getController();
                adminstatusListControllerCustomer.setData(userBlocked.get(i));
                userStatusList.getChildren().add(anchorPane);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML private void switchMode(ActionEvent event){
        ThemeController.switchMode(parent);
        try {
            com.github.saacsos.FXRouter.setAnimationType("fade",250);
            com.github.saacsos.FXRouter.goTo("admin-blocked-list");
            com.github.saacsos.FXRouter.setAnimationType("fade",0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void userListButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-user-view",admin);
        }catch (IOException e){
            System.err.println("ไปหน้า userList ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }

    @FXML
    void userReportButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-reported-list",admin);
        }catch (IOException e){
            System.err.println("ไปหน้า userReport ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }

    @FXML
    void goToEditProfile(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("admin-change-password",admin);
        }catch (IOException e){
            System.err.println("ไปที่หน้า admin-change-password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void productReportButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-reported-product-list",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin-reported-product-list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void addProductType(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-add-product-type", admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin-add-product-type ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void clickLogoGoToAdminUserView(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("admin-user-view");
        }catch (IOException e){
            System.err.println("ไปหน้า userList ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }
}

