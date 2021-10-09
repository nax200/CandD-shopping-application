package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.StockTotal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockTotalListController implements Initializable {

    @FXML private ImageView img;
    @FXML private Label id_Product;
    @FXML private Label nameProduct;
    @FXML private Label price;
    @FXML private Label quantity;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Product product){
        Image imgProfile;
        imgProfile = new Image(getClass().getResourceAsStream(product.getImageFilePath()));
        img.setImage(imgProfile);
        id_Product.setText(product.getID());
        nameProduct.setText(product.getName());
        price.setText(product.getPriceString());
        quantity.setText(product.getRemaining()+"");
    }

    @FXML
    public void handleEditAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit-add-shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit-add-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}//end
