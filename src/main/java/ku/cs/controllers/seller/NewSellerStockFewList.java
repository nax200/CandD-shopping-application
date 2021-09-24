package ku.cs.controllers.seller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.sellercontact.NewStokTotal;

import java.net.URL;
import java.util.ResourceBundle;

public class NewSellerStockFewList implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label id_Product;

    @FXML
    private Label nameProduct;

    @FXML
    private Label price;

    @FXML
    private Label quantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(NewStokTotal prototype){
        Image imgProfile;
        imgProfile = new Image(getClass().getResourceAsStream(prototype.getImgSrc()));
        img.setImage(imgProfile);

        id_Product.setText(prototype.getId_Product());
        nameProduct.setText(prototype.getNameProduct());
        price.setText(prototype.getPrice());
        quantity.setText(prototype.getQuantity());
    }
}//end
