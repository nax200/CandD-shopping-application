package ku.cs.controllers.admin.report.product;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.product.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductDetailPopupController implements Initializable {

    @FXML private ImageView productImage;
    @FXML private TextField name;
    @FXML private TextField price;
    @FXML private TextField remain;
    @FXML private TextField warning;
    @FXML private TextField type;
    @FXML private TextArea des;
    @FXML private AnchorPane parent;

    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void initData(Stage stage){
        this.stage = stage;
    }


    public void setData(Product product){
        name.setText(product.getName());
        price.setText(product.getPriceString());
        remain.setText(product.getRemaining()+"");
        warning.setText(product.getNumRemainWarning()+"");
        type.setText("no type");
        des.setText(product.getDetail());
        Image image = new Image("file:"+product.getImageFilePath(),true);
        productImage.setImage(image);
    }



    @FXML public void closeButton(ActionEvent event){
        stage.close();
    }
}
