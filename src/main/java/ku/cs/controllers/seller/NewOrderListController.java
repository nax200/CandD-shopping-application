package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.NewOrder;
import ku.cs.models.shop.Order;
import ku.cs.models.shop.OrderList;
import ku.cs.models.shop.Product;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;
import ku.cs.services.DataSource;
import ku.cs.services.OrderFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
    private TextField trackingNumber;

    @FXML
    private Label userName;

    @FXML
    private Button addTrackingNumber;

    @FXML
    private Label messageLabel;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }



    public void setData(Order order){
        Image imgProfile;
        Product product = order.getProduct();
        imgProfile = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(imgProfile);

        id_Product.setText(order.getOrderNo());
        userName.setText(((User) order.getBuyer()).getUsername());
        nameProduct.setText(product.getName());
        priceSum.setText(""+order.getTotalPrice());
        quantity.setText(""+order.getQuantity());
        trackingNumber.setText(order.getTrackingNumber());
    }



    @FXML
    void addTrackingNumber(ActionEvent event){
        DataSource<OrderList> dataSource;
        dataSource = new OrderFileDataSource();
        OrderList orderList = dataSource.readData();

        if (!trackingNumber.getText().equals("")) {
            Order order = orderList.searchByOrderNo(id_Product.getText().trim());
            order.setTrackingNumber(trackingNumber.getText());
            dataSource.writeData(orderList);

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
