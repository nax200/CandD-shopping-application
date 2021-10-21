package ku.cs.controllers.market.productdetail;
import javafx.event.ActionEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.comment.Comment;
import ku.cs.models.user.Customer;
import ku.cs.models.user.LoginCustomer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentCardController implements Initializable {


    @FXML
    private Label userName;

    @FXML
    private Label commentLabel;

    @FXML
    private Label timeToComment;

    @FXML
    private Circle profileImage;

    @FXML
    private Label ratingLabel;

    @FXML
    AnchorPane parent;

    private User user;
    private Comment comment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void setData(Comment comment) {
        this.comment = comment;
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userAll = dataSource.readData();
        user = userAll.searchUsername(comment.getSender());
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(((Customer) user).getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        profileImage.setFill(new ImagePattern(image));
        userName.setText(comment.getSender());
        if (comment.getComment().equals("")) {
            commentLabel.setText("ไม่มีความคิดเห็น");
            commentLabel.setTextFill(Color.rgb(156, 156, 156));
        } else {
            commentLabel.setText(comment.getComment());
        }
        timeToComment.setText(comment.getTimeToCommentToString());
        ratingLabel.setText("" + comment.getRating());
    }

    @FXML
    public void reportUser(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ku/cs/marketpage/productdetail/report.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            ReportPopupController reportPopupController = fxmlLoader.getController();

            reportPopupController.setData((Customer) user, LoginCustomer.customer, comment, stage);
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("เปิดหน้าต่าง pop-up ไม่ได้");
            e.printStackTrace();
        }
    }
}


