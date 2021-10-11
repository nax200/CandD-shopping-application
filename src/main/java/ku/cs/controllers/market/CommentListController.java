package ku.cs.controllers.market;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import ku.cs.models.shop.Comment;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentListController implements Initializable {


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

    public void setData(Comment comment){
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userAll = dataSource.readData();
        User user = userAll.searchUsername(comment.getNameToComment()) ;
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(((Customer)user).getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        profileImage.setFill(new ImagePattern(image));
        userName.setText(comment.getNameToComment());
        if(comment.getComment().equals("")) {
            commentLabel.setText("ไม่มีความคิดเห็น");
            commentLabel.setTextFill(Color.rgb(156,156,156));
        }
        else {
            commentLabel.setText(comment.getComment());
        }
        timeToComment.setText(comment.getTimeToCommentToString());
        ratingLabel.setText(""+comment.getRating());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
