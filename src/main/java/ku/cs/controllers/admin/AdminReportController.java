package ku.cs.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.admin.*;
import ku.cs.models.user.User;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFileDataSource;

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
       loadReportUser();
    }

    public void loadReportUser(){
        userReportList.getChildren().removeAll();
        userReportList.getChildren().addAll();
        admin = (User) com.github.saacsos.FXRouter.getData();
        DataSource<ReportList> dataSource;
        dataSource = new ReportFileDataSource();
        ReportList reportList = dataSource.readData();
        ConditionFilterer<Report> filterer = new ConditionFilterer<Report>() {
            @Override
            public boolean match(Report report) {
                if (report instanceof ReportedComment && report.getChecked()) {
                    return  false;
                }
                return true;
            }
        };
        ArrayList<Report> reports = reportList.filter(filterer);
        for(int i = 0;i<reports.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/admin-report-list.fxml"));
            try {
                if(reports.get(i) instanceof ReportedComment) {
                    HBox hBox = fxmlLoader.load();
                    AdminReportListController adminreportListController = fxmlLoader.getController();
                    adminreportListController.setData(reports.get(i));
                    userReportList.getChildren().add(hBox);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
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
}

