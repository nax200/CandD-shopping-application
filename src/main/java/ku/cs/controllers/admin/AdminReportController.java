package ku.cs.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.admin.AdminUserReport;
import ku.cs.models.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminReportController implements Initializable {

    @FXML
    private VBox userReportList;
    private User admin;
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
    void userStatusButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-blocked-list",admin);
        }catch (IOException e){
            System.err.println("ไปหน้า userStatus ไม่ได้");
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
        List<AdminUserReport> users = new ArrayList<>(adminUserReports());
        admin = (User) com.github.saacsos.FXRouter.getData();
        for(int i = 0;i<users.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/admin-report-list.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                AdminReportListController adminreportListController = fxmlLoader.getController();
                adminreportListController.setData(users.get(i));
                userReportList.getChildren().add(hBox);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private List<AdminUserReport> adminUserReports(){
        List<AdminUserReport> ls = new ArrayList<>();
        AdminUserReport user = new AdminUserReport();

        user.setUsername("moss");
        user.setImgSrc("/images/creditpage/moss.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(true);
        user.setMoreDetailReport("-");
        user.setMessagetoReport("-");
        user.setReportType("-");
        ls.add(user);

        user = new AdminUserReport();
        user.setUsername("nax");
        user.setImgSrc("/images/creditpage/nax.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(true);
        user.setMoreDetailReport("-");
        user.setMessagetoReport("-");
        user.setReportType("-");
        ls.add(user);

        user = new AdminUserReport();
        user.setUsername("bam");
        user.setImgSrc("/images/creditpage/bamboo.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(true);
        user.setMoreDetailReport("-");
        user.setMessagetoReport("-");
        user.setReportType("-");
        ls.add(user);

        user = new AdminUserReport();
        user.setUsername("ploy");
        user.setImgSrc("/images/creditpage/ploy.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserBlocked(true);
        user.setMoreDetailReport("-");
        user.setMessagetoReport("-");
        user.setReportType("-");
        ls.add(user);

        return  ls;
    }
}

