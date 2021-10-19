package ku.cs.controllers.market.marketplace;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import com.github.saacsos.FXRouter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.product.Product;
import ku.cs.models.shop.product.ProductList;
import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.UserFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class SelectedShopController implements Initializable {

    @FXML private ComboBox<String> sortComboBox;
    @FXML private GridPane listProduct;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private Label shopName;
    @FXML private TextField searchTextField;
    @FXML private Label countProduct;
    @FXML private AnchorPane parent;
    @FXML private Circle shopImg;
    private Product product;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        product = (Product) FXRouter.getData();
        showShopData();

        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        Customer seller = (Customer) userList.searchByShopName(product.getShopName());
        BufferedImage bufferedShopImage = null;
        try {
            bufferedShopImage = ImageIO.read( seller.getImageFile() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedShopImage, null);

        shopImg.setFill(new ImagePattern(image));
        sortComboBox.getItems().addAll("ล่าสุด","ราคาน้อยไปมาก", "ราคามากไปน้อย");
        sortComboBox.setValue("ล่าสุด");

        ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
            @Override
            public boolean match(Product product) {
                return product.getShopName().equals(shopName.getText());
            }
        };

        sortComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sortComboBox.getValue().equals("ล่าสุด")) {
                    sortByLatest(filterer);
                }
                else if (sortComboBox.getValue().equals("ราคาน้อยไปมาก")) {
                    sortByPriceMinToMax(filterer);
                }
                else if (sortComboBox.getValue().equals("ราคามากไปน้อย")) {
                    sortByPriceMaxToMin(filterer);
                }
            }
        });
        sortByLatest(filterer);

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());
    }

    public void sortByLatest(ConditionFilterer<Product> filterer) {
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
        productList.sort(productComparator);
        ArrayList<Product> products = productList.filter(filterer);
        int column  = 0;
        int row = 1;
        try {
            sortComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (sortComboBox.getValue().equals("ล่าสุด")) {
                        sortByLatest(filterer);
                    }
                    else if (sortComboBox.getValue().equals("ราคาน้อยไปมาก")) {
                        sortByPriceMinToMax(filterer);
                    }
                    else if (sortComboBox.getValue().equals("ราคามากไปน้อย")) {
                        sortByPriceMaxToMin(filterer);
                    }
                }
            });
            for (int i = 0; i < products.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/marketplace/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setData(products.get(i));
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
    }

    public void sortByPriceMaxToMin(ConditionFilterer<Product> filterer){
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() < o2.getPrice()) return 1;
                if (o1.getPrice() > o2.getPrice()) return -1;
                return 0;
            }
        };
        int column  = 0;
        int row = 1;
        productList.sort(productComparator);
        ArrayList<Product> products = productList.filter(filterer);
        try {
            sortComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (sortComboBox.getValue().equals("ล่าสุด")) {
                        sortByLatest(filterer);
                    }
                    else if (sortComboBox.getValue().equals("ราคาน้อยไปมาก")) {
                        sortByPriceMinToMax(filterer);
                    }
                    else if (sortComboBox.getValue().equals("ราคามากไปน้อย")) {
                        sortByPriceMaxToMin(filterer);
                    }
                }
            });
            for (int i = 0; i < products.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/marketplace/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setData(products.get(i));
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
    }

    public void sortByPriceMinToMax(ConditionFilterer<Product> filterer) {
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() > o2.getPrice()) return 1;
                if (o1.getPrice() < o2.getPrice()) return -1;
                return 0;
            }
        };
        int column  = 0;
        int row = 1;
        productList.sort(productComparator);
        ArrayList<Product> products = productList.filter(filterer);
        try {
            sortComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (sortComboBox.getValue().equals("ล่าสุด")) {
                        sortByLatest(filterer);
                    }
                    else if (sortComboBox.getValue().equals("ราคาน้อยไปมาก")) {
                        sortByPriceMinToMax(filterer);
                    }
                    else if (sortComboBox.getValue().equals("ราคามากไปน้อย")) {
                        sortByPriceMaxToMin(filterer);
                    }
                }
            });
            for (int i = 0; i < products.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/marketplace/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setData(products.get(i));
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
    }

    public void showShopData(){
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
            @Override
            public boolean match(Product product) {
                return product.getShopName().equals(shopName.getText());
            }
        };
        shopName.setText(product.getShopName());
        countProduct.setText(String.valueOf(productList.filter(filterer).size()));
    }

    public void searchProduct(){
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
            @Override
            public boolean match(Product product) {
                return product.getName().equals(searchTextField.getText()) && product.getShopName().equals(shopName.getText());
            }
        };
        sortComboBox.setValue("ล่าสุด");
        listProduct.getChildren().removeAll();
        listProduct.getChildren().setAll();
        sortByLatest(filterer);
        countProduct.setText(String.valueOf(productList.filter(filterer).size()));
    }

    @FXML
    public void goToEditProfile(ActionEvent event) {
        try {
            FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void logOut(ActionEvent event) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void goToOpenShop(ActionEvent event){
        try {
            if(LoginCustomer.customer.getShopName().equals("-")) {
                FXRouter.goTo("open-shop");
            }
            else {
                FXRouter.goTo("stock-total");
            }
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
