package ku.cs.controllers.seller.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.order.Order;
import ku.cs.models.shop.order.OrderList;
import ku.cs.models.shop.product.Product;
import ku.cs.models.shop.promotion.PromotionBaht;
import ku.cs.models.shop.promotion.PromotionPercent;
import ku.cs.models.user.User;
import ku.cs.services.DataSource;
import ku.cs.services.OrderFileDataSource;
import ku.cs.models.shop.product.ProductList;
import ku.cs.services.ProductFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewOrderListController implements Initializable {
    @FXML private ImageView img;
    @FXML private Label id_Product;
    @FXML private Label nameProduct;
    @FXML private Label priceSum;
    @FXML private Label quantity;
    @FXML private TextField trackingNumber;
    @FXML private Label userName;
    @FXML private Label messageLabel;
    @FXML private AnchorPane parent;
    @FXML private Label promotionLabel;
    @FXML private Label dateLabel;
    @FXML private TextArea addressTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }



    public void setData(Order order){
        Image imgProfile;
        Product product = order.getProduct();
        imgProfile = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(imgProfile);

        id_Product.setText(order.getOrderNo());
        userName.setText(((User) order.getBuyer()).getUsername());
        nameProduct.setText(product.getName());
        quantity.setText(""+order.getQuantity());
        trackingNumber.setText(order.getTrackingNumber());
        promotionLabel.setText(order.getPromotionToString());
        addressTextArea.setText(order.getAddress());
        dateLabel.setText(order.getAddedTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        priceSum.setText(String.format("%.2f",order.getTotalPrice()));
        if(order.getPromotion() instanceof PromotionBaht){
            priceSum.setText(((PromotionBaht) order.getPromotion()).calculatePriceSum(order.getTotalPrice())+"");
        }
        else if (order.getPromotion() instanceof PromotionPercent){
            priceSum.setText(((PromotionPercent) order.getPromotion()).calculatePriceSum(order.getTotalPrice())+"");
        }
    }



    @FXML
    void addTrackingNumber(ActionEvent event){
        DataSource<OrderList> dataSource;
        dataSource = new OrderFileDataSource();
        OrderList orderList = dataSource.readData();

        DataSource<ProductList> dataSourceProduct;
        dataSourceProduct = new ProductFileDataSource();
        ProductList productList = dataSourceProduct.readData();

        if (!trackingNumber.getText().equals("")) {
            Order order = orderList.searchByOrderNo(id_Product.getText().trim());
            order.setTrackingNumber(trackingNumber.getText());
            order.setAddedTime(LocalDateTime.now());
            dataSource.writeData(orderList);

            Product product = productList.searchByName(nameProduct.getText());
            product.setRemaining(order.reduceQuantityInStock(Integer.parseInt(quantity.getText())));
            dataSourceProduct.writeData(productList);

            try {
                com.github.saacsos.FXRouter.goTo("new-order");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า new-order ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else {
            messageLabel.setText("*");
        }

    }

}//end
