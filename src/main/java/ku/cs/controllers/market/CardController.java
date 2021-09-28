package ku.cs.controllers.market;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ku.cs.models.shop.Product;
import java.io.IOException;

public class CardController {

    @FXML private ImageView img;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;

    public void setData(Product product){
        nameLabel.setText(product.getName());
        priceLabel.setText(product.getPriceString());
        Image image = new Image(getClass().getResourceAsStream(product.getImageFilePath()));
        img.setImage(image);
    }

    @FXML
    void goToProductPage(MouseEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("product");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}

