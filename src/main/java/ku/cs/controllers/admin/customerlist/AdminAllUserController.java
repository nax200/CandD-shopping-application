package ku.cs.controllers.admin.customerlist;
import com.github.saacsos.FXRouter;
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
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AdminAllUserController implements Initializable{
    @FXML
    private VBox userList;
    @FXML
    private AnchorPane parent;
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
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userAll = dataSource.readData();
        admin = (User) FXRouter.getData();
        Comparator<User> userComparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getLastTimeLoggedIn().isBefore(o2.getLastTimeLoggedIn()) ) return 1;
                if(o2.getLastTimeLoggedIn().isBefore(o1.getLastTimeLoggedIn())) return -1;
                return 0;
            }
        };
        for(int i=0 ;i<userAll.count();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/customerlist/admin-user-list.fxml"));
            try{
                if(userAll.getUser(i) instanceof Customer) {
                    AnchorPane anchorPane = fxmlLoader.load();
                    userAll.sortTime(userComparator);
                    AdminAllUserListController adminuserListControllerAll = fxmlLoader.getController();
                    adminuserListControllerAll.setData(userAll.getUser(i));
                    userList.getChildren().add(anchorPane);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @FXML private void switchMode(ActionEvent event){
        ThemeController.switchMode(parent);
        try {
            FXRouter.setAnimationType("fade",250);
            FXRouter.goTo("admin-user-view");
            FXRouter.setAnimationType("fade",0);
        } catch (IOException e) {
            e.printStackTrace();
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
    void userStatusButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-blocked-list",admin);
        }catch (IOException e){
            System.err.println("ไปหน้า userStatus ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }
    @FXML
    void productReportButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-reported-product-list",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin-reported-product-list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
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
            com.github.saacsos.FXRouter.goTo("admin-add-product-type");
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
