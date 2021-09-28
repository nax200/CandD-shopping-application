package ku.cs.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.admin.AdminUser;
import ku.cs.models.user.Admin;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUserController implements Initializable{
    @FXML
    private VBox userList;

    @FXML
    void userReportButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-reported-list");
        }catch (IOException e){
            System.err.println("ไปหน้า userReport ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }

    @FXML
    void userStatusButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-blocked-list");
        }catch (IOException e){
            System.err.println("ไปหน้า userStatus ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }

    @FXML
    void goToEditProfile(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("user-profile-edit");
        }catch (IOException e){
            System.err.println("ไปที่หน้า user-profile ไม่ได้");
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userAll = dataSource.readData();
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
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/admin-user-list.fxml"));
            try{
                if(userAll.getUser(i) instanceof Customer) {
                    HBox hBox = fxmlLoader.load();
                    userAll.sortTime(userComparator);
                    AdminUserListController adminuserListController = fxmlLoader.getController();
                    adminuserListController.setData(userAll.getUser(i));
                    userList.getChildren().add(hBox);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    private List<AdminUser> adminUsers(){
        List<AdminUser> ls = new ArrayList<>();
        AdminUser user = new AdminUser();

        user.setUsername("moss");
        user.setImgSrc("/images/creditpage/moss.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(false);
        ls.add(user);

        user = new AdminUser();
        user.setUsername("nax");
        user.setImgSrc("/images/creditpage/nax.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(false);
        ls.add(user);

        user = new AdminUser();
        user.setUsername("bam");
        user.setImgSrc("/images/creditpage/bamboo.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(false);
        ls.add(user);

        user = new AdminUser();
        user.setUsername("ploy");
        user.setImgSrc("/images/creditpage/ploy.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(true);
        ls.add(user);

        return ls;
    }

}
