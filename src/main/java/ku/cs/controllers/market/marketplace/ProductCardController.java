package ku.cs.controllers.market.marketplace;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.product.Product;
import java.io.IOException;

public class ProductCardController {

    @FXML private ImageView img;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private AnchorPane parent;
    private Product product;


    public void setData(Product product){
        ThemeController.setTheme(parent);
        this.product = product;
        nameLabel.setText(product.getName());
        priceLabel.setText(product.getPriceString());
        Image image = new Image("file:"+product.getImageFilePath(),true);
        img.setImage(image);
    }

    @FXML
    void goToProductPage(MouseEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("product", product);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}

