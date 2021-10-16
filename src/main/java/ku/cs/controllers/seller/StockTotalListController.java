package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.StockTotal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockTotalListController implements Initializable {

    @FXML private ImageView img;
    @FXML private Label id_Product;
    @FXML private Label nameProduct;
    @FXML private Label price;
    @FXML private Label quantity;
    @FXML private AnchorPane parent;

    private Product product;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void setData(Product product){
        this.product = product;
        Image imgProfile;
        imgProfile = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(imgProfile);
        id_Product.setText(product.getID());
        nameProduct.setText(product.getName());
        price.setText(product.getPriceString());
        if (product.getRemaining() < product.getNumRemainWarning()) {
            if (ThemeController.isLightMode) {
                quantity.setTextFill(Color.rgb(255, 84, 47)); //ff542f 255 84 47
            } else {
                quantity.setTextFill(Color.rgb(255, 144, 120));
            }
            quantity.setText(product.getRemaining() + "");
        } else {
            if (ThemeController.isLightMode) {
                quantity.setTextFill(Color.rgb(111, 111, 111)); //ff542f 255 84 47
            } else {
                quantity.setTextFill(Color.rgb(255, 255, 255));
            }
            quantity.setText(product.getRemaining() + "");
        }
    }

    @FXML
    public void handleEditAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit-add-shop",product);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit-add-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    public void increaseButton() {
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        Product targetProduct = productList.searchByID( product.getID() );
        targetProduct.increaseRemain();
        dataSource.writeData(productList);
        setData(targetProduct);
    }

    public void decreaseButton() {
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        Product targetProduct = productList.searchByID( product.getID() );
        targetProduct.decreaseRemain();
        dataSource.writeData(productList);
        setData(targetProduct);
    }
}//end
