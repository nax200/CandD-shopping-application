package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.shop.StockTotal;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Product;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;
import java.util.Comparator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StockTotalController implements Initializable {
    @FXML
    private VBox contactsLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getAddedTime().isBefore(o2.getAddedTime())) return 1;
                if (o2.getAddedTime().isBefore(o1.getAddedTime())) return -1;
                return 0;
            }
        };
//        List<StockTotal> prototype = new ArrayList<>(prototype());
        for (int i = 0; i < productList.count(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/stock-total-list.fxml"));
            productList.sort(productComparator);

            try {
                HBox hBox = fxmlLoader.load();
                StockTotalListController stockTotalList = fxmlLoader.getController();
                stockTotalList.setData(productList.getProduct(i));
                contactsLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
    public void handleEditShopButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit-shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleShippedOrderButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("shipped-order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shipped-order ไม่ได้");
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

    private List<StockTotal> prototype() {
        List<StockTotal> ls = new ArrayList<>();
        StockTotal prototype = new StockTotal();

        prototype.setId_Product("P2109180001");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        prototype.setPrice("199");
        prototype.setQuantity("3");
        prototype.setNameProduct("เสื้อแฟชั่น");
        ls.add(prototype);

        prototype = new StockTotal();
        prototype.setId_Product("P2109180002");
        prototype.setImgSrc("/images/marketpage/img_6.png");
        prototype.setPrice("259");
        prototype.setQuantity("5");
        prototype.setNameProduct("รองเท้าแฟชั่น");
        ls.add(prototype);

        prototype = new StockTotal();
        prototype.setId_Product("P2109180003");
        prototype.setImgSrc("/images/marketpage/img_7.png");
        prototype.setPrice("229");
        prototype.setQuantity("30");
        prototype.setNameProduct("กระโปรงแฟชั่น");
        ls.add(prototype);

        prototype = new StockTotal();
        prototype.setId_Product("P2109180004");
        prototype.setImgSrc("/images/marketpage/img_8.png");
        prototype.setPrice("899");
        prototype.setQuantity("15");
        prototype.setNameProduct("กางเกงแฟชั่น");
        ls.add(prototype);
        return ls;
    }
}//end
