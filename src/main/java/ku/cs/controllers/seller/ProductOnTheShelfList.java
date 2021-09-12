package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.sellercontact.ProductOnTheShelfprototypes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductOnTheShelfList implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label nameProduct;

    @FXML
    private Label treasury;

    @FXML
    private Label price;

    @FXML
    private Label note;

    @FXML
    private Button modify;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(ProductOnTheShelfprototypes prototype) {
        Image imgProfile;
        imgProfile = new Image(getClass().getResourceAsStream(prototype.getImgSrc()));
        img.setImage(imgProfile);

        nameProduct.setText(prototype.getNameProduct());
        treasury.setText(prototype.getTreasury());
        price.setText(prototype.getPrice());
        note.setText(prototype.getNote());


    }

    @FXML
    public void handleEditItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("EditItem");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า EditItem ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

}//end
