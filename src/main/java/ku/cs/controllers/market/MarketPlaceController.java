package ku.cs.controllers.market;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.models.shop.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.github.saacsos.FXRouter;
import ku.cs.models.shop.ProductList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;
import java.util.Comparator;


public class MarketPlaceController implements Initializable{

    @FXML private ChoiceBox<String> category;
    @FXML private ChoiceBox<String> sort;
    @FXML private Label categoryLabel;
    @FXML private GridPane grid;

    private List<Product> products = new ArrayList<>();

    private List<Product> getData(){
        List<Product> products = new ArrayList<>();
        Product product;

        for (int i = 0; i < 20; i++){
            product = new Product();
            product.setName("เสื้อแฟชั่นลายฮิต");
            product.setPrice(250);
            product.setImageFilePath("/images/marketpage/img_1.png");
            products.add(product);
        }
        return products;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort.getItems().addAll("ราคาน้อยไปมาก", "ราคามากไปน้อย");
        category.getItems().addAll("เครื่องแต่งกาย", "เสื้อผ้าแฟชั่น");
        category.setOnAction(actionEvent -> categoryLabel.setText(category.getValue()));

        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getAddedTime().isBefore(o2.getAddedTime())) return 1;
                if (o2.getAddedTime().isBefore(o1.getAddedTime())) return -1;
                return 0;
            }
        };

        int column  = 0;
        int row = 1;
        try {
            for (int i = 0; i < productList.count(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                productList.sort(productComparator);

                CardController cardController = fxmlLoader.getController();
                cardController.setData(productList.getProduct(i));

                if(column == 4){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}