package ku.cs.controllers.seller.order;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.order.Order;
import ku.cs.models.shop.order.OrderList;
import ku.cs.models.shop.product.Product;
import ku.cs.models.shop.product.ProductList;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.DataSource;
import ku.cs.services.OrderFileDataSource;
import ku.cs.services.ProductFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ShippedOrderController implements Initializable {
    @FXML
    private VBox contactsLayout;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        DataSource<OrderList> dataSource;
        dataSource = new OrderFileDataSource();
        OrderList orderList = dataSource.readData();

        DataSource<ProductList> dataSource1;
        dataSource1 = new ProductFileDataSource();
        ProductList productList = dataSource1.readData();


        Comparator<Order> orderComparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getAddedTime().isAfter(o2.getAddedTime())) return -1;
                if (o2.getAddedTime().isAfter(o1.getAddedTime())) return 1;
                return 0;
            }
        };
        ConditionFilterer<Order> filterer1 = new ConditionFilterer<Order>() {
            @Override
            public boolean match(Order order) {
                Product product = productList.searchByID(order.getProduct().getID());
                return LoginCustomer.customer.getShopName().equals(product.getShopName());
            }
        };
        ArrayList<Order> orders = orderList.filter(filterer1);


        for (int i = 0; i < orders.size(); i++){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/order/shipped-order-list.fxml"));
            orders.sort(orderComparator);//ใช้ของอันใหม่

            try {
                if(!orders.get(i).getTrackingNumber().isEmpty()){
                AnchorPane anchorPane = fxmlLoader.load();
                ShippedOrderListController shippedOrderListController = fxmlLoader.getController();
                shippedOrderListController.setData(orders.get(i));
                contactsLayout.getChildren().add(anchorPane);}
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

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

    @FXML
    public void handleStockTotalButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("stock-total");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า stock-total ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleLowStockButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("low-stock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า low-stock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleNewOrderButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("new-order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า new-order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("add-item");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add-item ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleEditShopButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit-shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleCreatePromotionButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("create-promotion");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า create-promotion ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleAllPromotionCreateButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("all-promotion-create");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า all-promotion-create ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}//end
