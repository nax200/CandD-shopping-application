package ku.cs.controllers.market;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.shop.Order;
import ku.cs.models.shop.OrderList;
import ku.cs.models.shop.Product;
import ku.cs.models.user.LoginCustomer;
import com.github.saacsos.FXRouter;
import ku.cs.services.DataSource;
import ku.cs.services.OrderFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PurchaseController implements Initializable {
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private Label price;
    @FXML private Label allProductPrice;
    @FXML private ImageView img;
    @FXML private RadioButton ems;
    @FXML private RadioButton registered;
    @FXML private Label shippingCost;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private Label messageLabel;
    @FXML private Label messageLabel2;
    @FXML private Label quantityLabel;
    @FXML private Label quantity;
    @FXML private TextArea addressTextArea;
    private Order order;
    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        price.setText( ""+order.getTotalPrice() );
        Image image = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(image);
        allProductPrice.setText(String.format("%.2f", order.getTotalPrice() ));
        quantityLabel.setText( ""+order.getQuantity() );
        quantity.setText( ""+order.getQuantity() );

    }

    @FXML void shipping() {
        double price = 0;
        if (ems.isSelected()) {
            int emsPrice = 35;
            price += order.getTotalPrice() + emsPrice;
            shippingCost.setText(emsPrice + "");
            allProductPrice.setText(String.format("%.2f", price));
        }
        else if (registered.isSelected()) {
            int registeredPrice = 15;
            price += order.getTotalPrice() + registeredPrice;
            shippingCost.setText(registeredPrice + "");
            allProductPrice.setText(String.format("%.2f", price));
        }
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
            if (ems.isSelected() || registered.isSelected()) {
                DataSource<OrderList> dataSource;
                dataSource = new OrderFileDataSource();
                OrderList orderList = dataSource.readData();

                order.setAddedTime(LocalDateTime.now());
                order.setOrderNo( "R"+ String.format("%05d", orderList.count()+1));
                order.setAddress( addressTextArea.getText() );
                orderList.addOrder(order);
                dataSource.writeData(orderList);

                com.github.saacsos.FXRouter.goTo("order");
            }
            else {
                messageLabel.setText("เลือกรูปแบบการจัดส่ง");
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
