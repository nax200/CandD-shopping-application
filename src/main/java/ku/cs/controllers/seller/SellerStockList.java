package ku.cs.controllers.seller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.sellercontact.Stockprototypes;
import java.net.URL;
import java.util.ResourceBundle;

public class SellerStockList implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label nameProduct;

    @FXML
    private Label quantity;

    @FXML
    private Label price;

    @FXML
    private Label name;

    @FXML
    private Label productStatus;

    @FXML
    private Label parcelNumber;

    public void setData(Stockprototypes prototype){
       Image imgProfile;
        imgProfile = new Image(getClass().getResourceAsStream(prototype.getImgSrc()));
        img.setImage(imgProfile);

       name.setText(prototype.getName());
       quantity.setText(prototype.getQuantity());
       nameProduct.setText(prototype.getNameProduct());
       price.setText(prototype.getPrice());
       productStatus.setText(prototype.getProductStatus());
       parcelNumber.setText(prototype.getParcelNumber());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
