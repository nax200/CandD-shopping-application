package ku.cs.controllers.market;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.*;
import ku.cs.models.user.LoginCustomer;
import com.github.saacsos.FXRouter;
import ku.cs.models.user.UserList;
import ku.cs.services.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PurchaseController implements Initializable {
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private Label price;
    @FXML private Label allProductPrice;
    @FXML private ImageView img;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private Label messageLabel2;
    @FXML private Label quantityLabel;
    @FXML private Label quantity;
    @FXML private TextArea addressTextArea;
    @FXML private AnchorPane parent;
    @FXML private TextField codeIdTextField;
    @FXML private Label discountLabel;
    @FXML private Label percentLabel;
    @FXML private Label messageCodeLabel;
    private Order order;
    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        order = (Order) FXRouter.getData();
        product = order.getProduct();
        setChosenProduct();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());
    }

    public void setChosenProduct() {
        productName.setText(product.getName());
        productPrice.setText(product.getPriceString());
        price.setText(String.format("%.2f", order.getTotalPrice()));
        Image image = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(image);
        allProductPrice.setText(String.format("%.2f", order.getTotalPrice() ));
        quantityLabel.setText( ""+order.getQuantity() );
        quantity.setText( ""+order.getQuantity() );

    }

    @FXML
    void goToEditProfile(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void backToProductButton(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("product");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า product ไม่ได้");
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
    void confirmOrder(ActionEvent event){
        if (addressTextArea.getText().equals("")){
            messageLabel2.setText("โปรดใส่ที่อยู่จัดส่ง");
            return;
        }
        try {
            DataSource<OrderList> dataSource;
            dataSource = new OrderFileDataSource();
            OrderList orderList = dataSource.readData();

            order.setAddedTime(LocalDateTime.now());
            order.setOrderNo( "R"+ String.format("%05d", orderList.count()+1));
            order.setAddress( addressTextArea.getText() );
            orderList.addOrder(order);
            dataSource.writeData(orderList);

            DataSource<ProductList> dataSource2;
            dataSource2 = new ProductFileDataSource();
            ProductList productList = dataSource2.readData();

            Product remaining = productList.searchByID(product.getID());
            remaining.setRemaining(product.getRemaining()-Integer.parseInt(quantity.getText().trim()));
            dataSource2.writeData(productList);

            com.github.saacsos.FXRouter.goTo("order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void userCodeIdButton(ActionEvent event) {
        discountLabel.setText("0");
        percentLabel.setText("บาท");
        order.setIdPromotion(null);
        allProductPrice.setText(String.format("%.2f", order.getTotalPrice()));
        DataSource<PromotionList> dataSource;
        dataSource = new PromotionFileDataSource();
        PromotionList promotionList = dataSource.readData();
        DataSource<UserList> dataSourceUser;
        dataSourceUser = new UserFileDataSource();
        UserList userList = dataSourceUser.readData();
        String codeInput = codeIdTextField.getText().trim();
        if(codeInput.equals("")){
            return;
        }
        Promotion promotion = promotionList.searchPromotion(codeInput);
        if( promotion == null ) {
            messageCodeLabel.setText("กรอกโค้ดไม่ถูกต้อง");
            return;
        }
        if(!promotion.isShopName(userList.searchByShopName(product.getShopName()).getUsername())){
            messageCodeLabel.setText("โค้ดส่วนลดไม่สามารถใช้กับร้านนี้ได้");
            return;
        }
        if(promotion instanceof PromotionBaht){
            if(order.getQuantity() >=((PromotionBaht) promotion).getMinimumAmount()){
                discountLabel.setText(((PromotionBaht) promotion).getBaht()+"");
                allProductPrice.setText(((PromotionBaht) promotion).getCalculator(order.getTotalPrice())+"");
                order.setIdPromotion(promotion);
                messageCodeLabel.setText("");
            }else {
                messageCodeLabel.setText("เงื่อนไขโค้ดไม่ถูกต้อง");
                return;
            }
        }else if(promotion instanceof PromotionPercent){
            if(order.getTotalPrice() >= ((PromotionPercent) promotion).getMinimumPurchase()){
                discountLabel.setText(((PromotionPercent) promotion).getPercent()+"");
                percentLabel.setText("%");
                allProductPrice.setText(((PromotionPercent) promotion).getCalculator(order.getTotalPrice())+"");
                order.setIdPromotion(promotion);
                messageCodeLabel.setText("");
            }else {
                messageCodeLabel.setText("เงื่อนไขโค้ดไม่ถูกต้อง");
                return;
            }
        }
    }

}
