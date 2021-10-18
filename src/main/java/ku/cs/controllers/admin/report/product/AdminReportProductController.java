package ku.cs.controllers.admin.report.product;

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
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import ku.cs.models.report.ReportedProduct;
import ku.cs.models.user.User;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminReportProductController implements Initializable {

    @FXML private VBox productReportList;
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
        DataSource<ReportList> reportListDataSource;
        reportListDataSource = new ReportFileDataSource();
        ReportList reportList = reportListDataSource.readData();
        ConditionFilterer<Report> filterer = new ConditionFilterer<Report>() {
            @Override
            public boolean match(Report report) {
                if (report instanceof ReportedProduct && report.getChecked()) {
                    return  false;
                }
                return true;
            }
        };
        ArrayList<Report> reports = reportList.filter(filterer);
        for(int i = 0;i<reports.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/reportlist/product/admin-report-product-list.fxml"));
            try {
                if(reports.get(i) instanceof ReportedProduct) {
                    AnchorPane anchorPane = fxmlLoader.load();
                    AdminReportProductListController adminReportProductListController = fxmlLoader.getController();
                    adminReportProductListController.setData(reports.get(i));
                    productReportList.getChildren().add(anchorPane);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @FXML private void switchMode(ActionEvent event){
        ThemeController.switchMode(parent);
        try {
            com.github.saacsos.FXRouter.setAnimationType("fade",250);
            com.github.saacsos.FXRouter.goTo("admin-reported-product-list");
            com.github.saacsos.FXRouter.setAnimationType("fade",0);
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
    void productReportButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-reported-product-list",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin-reported-product-list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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
