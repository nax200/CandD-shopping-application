package ku.cs.controllers.seller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.NewOrder;
import ku.cs.models.shop.Order;
import ku.cs.models.shop.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class NewOrderListController implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label id_Product;

    @FXML
    private Label nameProduct;

    @FXML
    private Label priceSum;

    @FXML
    private Label quantity;

    @FXML
    private Label trackingNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Order order){
        Image imgProfile;
        Product product = order.getProduct();
        imgProfile = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(imgProfile);

        id_Product.setText(order.getOrderNo());
        nameProduct.setText(product.getName());
        priceSum.setText(""+order.getTotalPrice());
        quantity.setText(""+order.getQuantity());
        trackingNumber.setText(order.getTrackingNumber());
    }

}//end
