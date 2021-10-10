package ku.cs.controllers.market;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.github.saacsos.FXRouter;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.shop.Product;
import ku.cs.models.user.LoginCustomer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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
    @FXML private ComboBox scoreProduct;
    @FXML private Label messageLabel;
    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        color.getItems().addAll("สีแดง", "สีชมพู", "สีเหลือง", "สีฟ้า");
        size.getItems().addAll("S", "M", "L", "XL");
        scoreProduct.getItems().addAll("1", "2", "3", "4", "5");
        product = (Product) FXRouter.getData();
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
        String quantityString = quantityTextField.getText();
        int quantity = Integer.parseInt(quantityString);
        product.setQuantity(Integer.parseInt(quantityString));
        try {
            if (quantity <= 0) {
                messageLabel.setText("จำนวนสินค้าไม่ถูกต้อง");
            }
            else if (quantity <= product.getRemaining()){
                FXRouter.goTo("purchase", product);
            }
            else {
                messageLabel.setText("จำนวนสินค้ามีไม่เพียงพอ");
            }
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
}
