package ku.cs.controllers.market;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import com.github.saacsos.FXRouter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.shop.Product;
import ku.cs.models.user.LoginCustomer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML private ChoiceBox<String> sort;
    @FXML private GridPane listProduct;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;

    private List<Product> products = new ArrayList<>();

    private List<Product> getData(){
        List<Product> products = new ArrayList<>();
        Product product;

        for (int i = 0; i < 10; i++){
            product = new Product();
            product.setName("เสื้อแฟชั่นลายฮิต");
            product.setPrice(250);
            product.setImageFilePath("/images/marketpage/img_1.png");
            products.add(product);
        }
        return products;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        sort.getItems().addAll("ราคาน้อยไปมาก", "ราคามากไปน้อย");

        products.addAll(getData());
        int column  = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(products.get(i));

                if(column == 5){
                    column = 0;
                    row++;
                }
                listProduct.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());
    }

    @FXML
    void goToEditProfile(ActionEvent event) {
        try {
            FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToOpenShop(ActionEvent event){
        try {
            FXRouter.goTo("open-shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า open-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void backToProductButton(ActionEvent event){
        try {
            FXRouter.goTo("product");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
