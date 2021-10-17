package ku.cs.controllers.seller.stock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.product.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LowStockListController implements Initializable {
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
        Image imgProfile = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(imgProfile);
        id_Product.setText(product.getID());
        nameProduct.setText(product.getName());
        price.setText(product.getPriceString());
        quantity.setText(product.getRemaining()+"");
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
}//end
