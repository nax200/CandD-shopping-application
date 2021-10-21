package ku.cs.controllers.market.productdetail;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.github.saacsos.FXRouter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;

import ku.cs.models.shop.order.Order;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ku.cs.models.shop.comment.Comment;
import ku.cs.models.shop.comment.CommentList;

import ku.cs.models.shop.product.Product;
import ku.cs.models.shop.product.ProductList;
import ku.cs.models.user.Customer;
import ku.cs.models.user.LoginCustomer;

import ku.cs.services.*;

import ku.cs.models.user.UserList;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ProductDetailController implements Initializable {

    @FXML private ImageView img;
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private TextField quantityTextField;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private Label remainInStock;
    @FXML private Circle imageProfileComment;
    @FXML private Label shopName;
    @FXML private TextArea productDes;
    @FXML private ComboBox rating;
    @FXML private Label messageLabel;
    @FXML private TextField messageComment;
    @FXML private VBox commentProduct;
    @FXML private Label reviewCount;
    @FXML private Label ratingAverage;
    @FXML private AnchorPane parent;
    @FXML private Label type;
    @FXML private Circle imageShop;
    private Product product;
    private int quantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
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

        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        Customer seller = (Customer) userList.searchByShopName(product.getShopName());

        try {
            bufferedImage = ImageIO.read( seller.getImageFile() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageShop.setFill(new ImagePattern(image));
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
        type.setText(product.getType());
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
            Order order = new Order(LoginCustomer.customer, product, quantity,null);
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
            if(messageComment.getText().trim().equals("")){
                messageComment.setText("ไม่มีความคิดเห็น");
            }
            Comment comment = new Comment("M"+String.format("%05d",commentList.count()+1),product.getID(),LoginCustomer.customer.getUsername(),messageComment.getText().trim(),localDateTime,Integer.parseInt(score),true);
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
    private void loadComment(){
        commentProduct.getChildren().removeAll();
        commentProduct.getChildren().setAll();
        DataSource<CommentList> dataSource;
        dataSource = new CommentFileDataSource();

        DataSource<ProductList> productDateSource;
        productDateSource = new ProductFileDataSource();
        ProductList productList = productDateSource.readData();

        CommentList commentAtProduct = dataSource.readData();
        reviewCount.setText("("+commentAtProduct.countCommentInProduct(product.getID())+")");

        DecimalFormat df = new DecimalFormat("#.#");
        Double rating = commentAtProduct.ratingAverage(product.getID());
        ratingAverage.setText(""+String.format("%.1f",rating)+"/5");
        productList.searchByID(product.getID()).setRating(Double.parseDouble(df.format(rating)));
        productDateSource.writeData(productList);
        Comparator<Comment> commentComparator = new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getCommentTime().isBefore(o2.getCommentTime())) return 1;
                if(o2.getCommentTime().isBefore(o1.getCommentTime())) return -1;
                return 0;
            }
        };
        ConditionFilterer<Comment> filterer = new ConditionFilterer<Comment>() {
            @Override
            public boolean match(Comment comment) {
                return product.getID().equals(comment.getIdProduct()) && comment.getVisible();
            }
        };
        commentAtProduct.sortTime(commentComparator);
        ArrayList<Comment> comments = commentAtProduct.filter(filterer);
        for (int i=0; i < comments.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/productdetail/product-comment-list.fxml"));
            try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    CommentCardController commentCardController = fxmlLoader.getController();
                    commentCardController.setData(comments.get(i));
                    commentProduct.getChildren().add(anchorPane);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void increaseButton() {
        messageLabel.setText("");
        if (quantityTextField.getText().equals("")) {
            quantityTextField.setText("0");
        }
        try {
            quantity = Integer.parseInt(quantityTextField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("จำนวนสินค้าไม่ถูกต้อง");
            return;
        }

        if (quantity < product.getRemaining()){
            quantity += 1;
            quantityTextField.setText(quantity+"");
        }
        else {
            quantityTextField.setText(quantity+"");
        }
    }

    public void decreaseButton() {
        messageLabel.setText("");
        if (quantityTextField.getText().equals("")) {
            quantityTextField.setText("1");
        }
        if (quantity > 1) {
            try {
                quantity = Integer.parseInt(quantityTextField.getText());
            } catch (NumberFormatException e) {
                messageLabel.setText("จำนวนสินค้าไม่ถูกต้อง");
                return;
            }
            quantity -= 1;
            quantityTextField.setText(quantity+"");
        }
    }

    @FXML
    void reportProduct(ActionEvent event) {
        try {
            DataSource<UserList> dataSource;
            dataSource = new UserFileDataSource();
            UserList userList = dataSource.readData();
            Customer reportedSeller = (Customer)userList.searchByShopName(product.getShopName());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ku/cs/marketpage/productdetail/report.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            ReportPopupController reportPopupController = fxmlLoader.getController();

            reportPopupController.setData( reportedSeller, LoginCustomer.customer, product, stage);
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("เปิดหน้าต่าง pop-up ไม่ได้");
            e.printStackTrace();
        }
    }
}
