package ku.cs.controllers.market;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.github.saacsos.FXRouter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.shop.*;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.DataSource;
import ku.cs.services.CommentFileDataSource;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.ProductFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML private ImageView img;
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private TextField quantityTextField;
    @FXML private ChoiceBox<String> color;
    @FXML private ChoiceBox<String> size;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private Label remainInStock;
    @FXML private Circle imageProfileComment;
    @FXML private Label shopName;
    @FXML private Label productDes;
    @FXML private ComboBox rating;
    @FXML private Label messageLabel;
    @FXML private TextField messageComment;
    @FXML private VBox commentProduct;
    @FXML private Label reviewCount;
    @FXML private Label ratingAverage;
    private Product product;
    private int quantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        color.getItems().addAll("สีแดง", "สีชมพู", "สีเหลือง", "สีฟ้า");
        size.getItems().addAll("S", "M", "L", "XL");
        rating.setValue("");
        rating.getItems().addAll("1", "2", "3", "4", "5");
        product = (Product) FXRouter.getData();
        quantity = 1;
        setChosenProduct();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        imageProfileComment.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());
        loadComment();
    }

    public void setChosenProduct() {
        productName.setText(product.getName());
        productPrice.setText(product.getPriceString());
        Image image = new Image( "file:"+product.getImageFilePath(),true );
        img.setImage(image);
        remainInStock.setText(product.getRemaining()+"");
        shopName.setText(product.getShopName());
        productDes.setText(product.getDetail());
    }

    @FXML
    public void backToMarketPlaceButton(ActionEvent event){
        try {
            FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void goToShopButton(ActionEvent event){
        try {
            FXRouter.goTo("shop", product);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToEditProfile(ActionEvent event) {
        try {
            FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToPurchase(ActionEvent event) {
        try {
            int quantity = Integer.parseInt(quantityTextField.getText());
            Order order = new Order(LoginCustomer.customer, product, quantity);
            if (quantity <= 0) {
                messageLabel.setText("จำนวนสินค้าไม่ถูกต้อง");
            } else if (quantity > product.getRemaining()) {
                messageLabel.setText("จำนวนสินค้ามีไม่เพียงพอ");
            } else {
                FXRouter.goTo("purchase", order);
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("จำนวนสินค้าไม่ถูกต้อง");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า purchase ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToOpenShop(ActionEvent event){
        try {
            if(LoginCustomer.customer.getShopName().equals("-")) {
                FXRouter.goTo("open-shop");
            }
            else {
                FXRouter.goTo("stock-total");
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า open-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void sendComment(ActionEvent event) {
        if(!rating.getValue().equals("")) {
            DataSource <CommentList> dataSource;
            dataSource = new CommentFileDataSource();
            CommentList commentList = dataSource.readData();
            LocalDateTime localDateTime = LocalDateTime.now();
            String score = ""+rating.getValue();
            Comment comment = new Comment("M"+String.format("%05d",commentList.count()+1),product.getID(),LoginCustomer.customer.getUsername(),messageComment.getText(),localDateTime,Integer.parseInt(score));
            commentList.addComment(comment);
            dataSource.writeData(commentList);
            messageComment.clear();
            rating.setValue("");
            loadComment();
        }
        else {

            return;
        }
    }
    private  void loadComment(){
        commentProduct.getChildren().removeAll();
        commentProduct.getChildren().setAll();
        DataSource<CommentList> dataSource;
        dataSource = new CommentFileDataSource();
        CommentList commentAtProduct = dataSource.readData();
        reviewCount.setText("("+commentAtProduct.countCommentInProduct(product.getID())+")");
        ratingAverage.setText(""+String.format("%.1f",commentAtProduct.ratingAverage(product.getID()))+"/5");
        Comparator<Comment> commentComparator = new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getTimeToComment().isBefore(o2.getTimeToComment())) return 1;
                if(o2.getTimeToComment().isBefore(o1.getTimeToComment())) return -1;
                return 0;
            }
        };
        ConditionFilterer<Comment> filterer = new ConditionFilterer<Comment>() {
            @Override
            public boolean match(Comment comment) {
                return product.getID().equals(comment.getIdProduct());
            }
        };
        commentAtProduct.sortTime(commentComparator);
        ArrayList<Comment> comments = commentAtProduct.filter(filterer);
        for (int i=0; i < comments.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/product-comment-list.fxml"));
            try {
                    HBox hBox = fxmlLoader.load();
                    CommentListController commentListController = fxmlLoader.getController();
                    commentListController.setData(comments.get(i));
                    commentProduct.getChildren().add(hBox);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void increaseButton() {
        quantity = Integer.parseInt(quantityTextField.getText());
        quantity += 1;
        quantityTextField.setText(quantity+"");
    }

    public void decreaseButton() {
        if (quantity >1) {
            quantity = Integer.parseInt(quantityTextField.getText());
            quantity -= 1;
            quantityTextField.setText(quantity+"");
        }
    }
}
