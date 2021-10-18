package ku.cs.controllers.admin;
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
import ku.cs.models.admin.Report;
import ku.cs.models.admin.ReportList;
import ku.cs.models.shop.Comment;
import ku.cs.models.shop.CommentList;
import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.CommentFileDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFileDataSource;
import ku.cs.services.UserFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminReportListController implements Initializable {
    @FXML
    private Circle profileImage;

    @FXML
    private Label username;

    @FXML
    private Label reportType;

    @FXML
    private TextArea moreDetailTextArea;

    @FXML
    private TextArea massageTextArea;

    @FXML
    private AnchorPane parent;

    @FXML
    private ComboBox<String> statusUserReport;
    private Customer customer ;
    private Report reportUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        DataSource<ReportList> dataSourceReport;
        dataSourceReport = new ReportFileDataSource();
        ReportList reportList = dataSourceReport.readData();

        DataSource<CommentList> dataSourceComment;
        dataSourceComment = new CommentFileDataSource();
        CommentList commentList = dataSourceComment.readData();
        statusUserReport.setValue("รอตรวจสอบ");
        statusUserReport.getItems().addAll("อนุมัติ","ไม่อนุมัติ");
        statusUserReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(statusUserReport.getValue().equals("อนุมัติ")){
                    DataSource<UserList> dataSource;
                    dataSource = new UserFileDataSource();
                    UserList userList = dataSource.readData();
                    (userList.searchUsername(customer.getUsername())).setStatus(true);
                    dataSource.writeData(userList);
                    Comment comment = commentList.searchIdComment(reportUser.getComment().getIdComment());
                    comment.setInvisible();
                }else if (statusUserReport.getValue().equals("ไม่อนุมัติ")) {
                    (reportList.searchReport(reportUser.getReportId())).setRecentCase(false);
                }
                dataSourceComment.writeData(commentList);
                reportList.searchReport(reportUser.getReportId()).setIsChecked();;
                dataSourceReport.writeData(reportList);
                try {
                    com.github.saacsos.FXRouter.goTo("admin-reported-list");
                }catch (IOException e){
                    System.err.println("ไปหน้า userReport ไม่ได้");
                    System.err.println("ให้ตรวจสอบ route");
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
        massageTextArea.setText(""+report.getComment().getComment());
        customer = report.getReportedName();
        reportUser = report;
    }
}
