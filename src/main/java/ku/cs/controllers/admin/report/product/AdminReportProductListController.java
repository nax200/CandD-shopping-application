package ku.cs.controllers.admin.report.product;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import ku.cs.models.report.ReportedProduct;
import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFileDataSource;
import ku.cs.services.UserFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminReportProductListController implements Initializable {
    @FXML
    private Circle profileImage;

    @FXML
    private Label username;

    @FXML
    private Label reportType;

    @FXML
    private TextArea moreDetailTextArea;

    @FXML
    private ComboBox<String> statusUserReport;

    @FXML private AnchorPane parent;

    private Customer customer ;
    private ReportedProduct reportProduct;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        DataSource<ReportList> dataSourceReport;
        dataSourceReport = new ReportFileDataSource();
        ReportList reportList = dataSourceReport.readData();
        statusUserReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(statusUserReport.getValue().equals("อนุมัติ")){
                    DataSource<UserList> dataSource;
                    dataSource = new UserFileDataSource();
                    UserList userList = dataSource.readData();
                    (userList.searchUsername(customer.getUsername())).setStatus(true);
                    dataSource.writeData(userList);
                }else if(statusUserReport.getValue().equals("ไม่อนุมัติ")){
                    (reportList.searchReport(reportProduct.getReportId())).setRecentCase(false);
                }
                reportList.searchReport(reportProduct.getReportId()).setIsChecked();;
                dataSourceReport.writeData(reportList);
                try {
                    com.github.saacsos.FXRouter.goTo("admin-reported-product-list");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า admin-reported-product-list ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                    e.printStackTrace();
                }
            }
        });
    }

    public void setData(Report report) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read((report).getReportedName().getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        profileImage.setFill(new ImagePattern(image));
        username.setText(report.getReportedName().getUsername());
        reportType.setText(report.getReportType());
        moreDetailTextArea.setText(""+report.getDetail());
        statusUserReport.setValue("รอตรวจสอบ");
        statusUserReport.getItems().addAll("อนุมัติ","ไม่อนุมัติ");
        customer = report.getReportedName();
        reportProduct = (ReportedProduct) report;
    }
    @FXML
    void seeProductButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ku/cs/adminpage/reportlist/product/product-detail-popup.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            ProductDetailPopupController productDetailPopupController = fxmlLoader.getController();
            productDetailPopupController.setData(reportProduct.getProduct());
            productDetailPopupController.initData(stage);
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("เปิดหน้าต่าง pop-up ไม่ได้");
            e.printStackTrace();
        }
    }

}


