package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.admin.AdminUserReport;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminReportListController implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label username;

    @FXML
    private Label reportType;

    @FXML
    private Label moreDetailReport;

    @FXML
    private Label messageReport;

    @FXML
    private ChoiceBox<String> statusUserReport;

    public void setData(AdminUserReport user){
        Image imgProfile = new Image(getClass().getResourceAsStream(user.getImgSrc()));
        img.setImage(imgProfile);
        username.setText(user.getUsername());
        reportType.setText(user.getReportType());
        moreDetailReport.setText(user.getMoreDetailReport());
        messageReport.setText(user.getMessagetoReport());
        statusUserReport.setValue("UnCheck");
        statusUserReport.getItems().addAll("UnCheck","Checked");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
