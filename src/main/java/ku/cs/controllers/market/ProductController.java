package ku.cs.controllers.market;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML private ImageView img;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private ChoiceBox<String> color;
    @FXML private ChoiceBox<String> size;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        color.getItems().addAll("สีแดง", "สีชมพู", "สีเหลือง", "สีฟ้า");
        size.getItems().addAll("S", "M", "L", "XL");
    }

    public void backToMarketPlaceButton(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("marketPlace");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า marketPlace ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void goToShopButton(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
