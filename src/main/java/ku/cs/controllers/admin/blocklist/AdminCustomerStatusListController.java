package ku.cs.controllers.admin.blocklist;
import javafx.scene.control.TextArea;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.report.ReportList;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFileDataSource;
import ku.cs.services.UserFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminCustomerStatusListController implements Initializable {
    @FXML
    private Circle profileImage;

    @FXML
    private Label username;

    @FXML
    private TextArea causeTextArea;

    @FXML
    private Label countTryToLogin;

    @FXML
    private ComboBox<String> userStatus;

    @FXML private AnchorPane parent;

    private Customer customer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        userStatus.getItems().addAll("ปกติ","ถูกจำกัด");
        userStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(userStatus.getValue().equals("ปกติ")){
                    (userList.searchUsername(customer.getUsername())).setStatus(false);
                    DataSource<ReportList> dataSourceReport;
                    dataSourceReport = new ReportFileDataSource();
                    ReportList reportList = dataSourceReport.readData();
                    reportList.searchCaseReportToSetLatest(customer.getUsername());
                    ((Customer)userList.searchUsername(customer.getUsername())).resetLoginAttempts();
                    dataSourceReport.writeData(reportList);
                }
                else {
                    (userList.searchUsername(customer.getUsername())).setStatus(true);
                }
                dataSource.writeData(userList);
                try {
                    com.github.saacsos.FXRouter.goTo("admin-blocked-list");
                }catch (IOException e){
                    System.err.println("ไปหน้า userStatus ไม่ได้");
                    System.err.println("ให้ตรวจสอบ route");
                }
            }
        });

    }

    public void setData(User user){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(((Customer)user).getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSource<ReportList> dataSourceReport;
        dataSourceReport = new ReportFileDataSource();
        ReportList reportList = dataSourceReport.readData();
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        profileImage.setFill(new ImagePattern(image));
        username.setText(user.getUsername());
        causeTextArea.setText(reportList.searchAllCheckedCase(user.getUsername()));
        countTryToLogin.setText(""+((Customer) user).getLoginAttempts());
        userStatus.setValue(((Customer)user).getIsUserBlockedToString());
        customer = (Customer)user;

    }
}

