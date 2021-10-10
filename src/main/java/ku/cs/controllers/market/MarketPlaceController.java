package ku.cs.controllers.market;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.shop.Product;
import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.models.shop.ProductList;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.DataSource;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.ProductFileDataSource;
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;


public class MarketPlaceController implements Initializable{

    @FXML private ComboBox<String> categoryComboBox;
    @FXML private ComboBox<String> sortComboBox;
    @FXML private Label categoryLabel;
    @FXML private GridPane listProduct;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private Button search;
    @FXML private TextField priceMinTextField;
    @FXML private TextField priceMaxTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortByLatest();
        sortComboBox.getItems().addAll("ล่าสุด","ราคาน้อยไปมาก", "ราคามากไปน้อย");
        categoryComboBox.getItems().addAll("เครื่องแต่งกาย", "เสื้อผ้าแฟชั่น");
        categoryComboBox.setOnAction(actionEvent -> categoryLabel.setText(categoryComboBox.getValue()));
        sortComboBox.setValue("ล่าสุด");
        sortComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sortComboBox.getValue().equals("ล่าสุด")) {
                    sortByLatest();
                }
                else if (sortComboBox.getValue().equals("ราคาน้อยไปมาก")) {
                    sortByPriceMinToMax();
                }
                else if (sortComboBox.getValue().equals("ราคามากไปน้อย")) {
                    sortByPriceMaxToMin();
                }
            }
        });
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listProduct.getChildren().removeAll();
                listProduct.getChildren().setAll();
                searchByPrice();
            }
        });

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

    public void sortByLatest() {
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

                listProduct.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortByPriceMaxToMin() {
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

                listProduct.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortByPriceMinToMax() {
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

                listProduct.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByPrice() {
        String priceMinText = priceMinTextField.getText();
        double priceMin = Integer.parseInt(priceMinText);
        String priceMaxText = priceMaxTextField.getText();
        double priceMax = Integer.parseInt(priceMaxText);
        sortComboBox.setValue("ล่าสุด");

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
            ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
                @Override
                public boolean match(Product product) {
                    return product.getPrice() >= priceMin && product.getPrice() <= priceMax;
                }
            };
            sortComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (sortComboBox.getValue().equals("ล่าสุด")) {
                        listProduct.getChildren().removeAll();
                        listProduct.getChildren().setAll();
                        DataSource<ProductList> dataSource;
                        dataSource = new ProductFileDataSource();
                        ProductList productList = dataSource.readData();

                        int column  = 0;
                        int row = 1;
                        try {
                            productList.sort(productComparator);
                            ArrayList<Product> products = productList.filter(filterer);

                            for (int i = 0; i < products.size(); i++){
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/card.fxml"));
                                AnchorPane anchorPane = fxmlLoader.load();

                                CardController cardController = fxmlLoader.getController();
                                cardController.setData(products.get(i));
                                if(column == 4){
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
                    else if (sortComboBox.getValue().equals("ราคาน้อยไปมาก")) {
                        listProduct.getChildren().removeAll();
                        listProduct.getChildren().setAll();
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
                        ArrayList<Product> products = productList.filter(filterer);

                        try {
                            for (int i = 0; i < products.size(); i++){
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/card.fxml"));
                                AnchorPane anchorPane = fxmlLoader.load();

                                products.sort(productComparator);

                                CardController cardController = fxmlLoader.getController();
                                cardController.setData(products.get(i));

                                if(column == 4){
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
                    else if (sortComboBox.getValue().equals("ราคามากไปน้อย")) {
                        listProduct.getChildren().removeAll();
                        listProduct.getChildren().setAll();
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
                        ArrayList<Product> products = productList.filter(filterer);

                        try {
                            for (int i = 0; i < products.size(); i++){
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/card.fxml"));
                                AnchorPane anchorPane = fxmlLoader.load();

                                products.sort(productComparator);

                                CardController cardController = fxmlLoader.getController();
                                cardController.setData(products.get(i));

                                if(column == 4){
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
                }
            });

            productList.sort(productComparator);
            ArrayList<Product> products = productList.filter(filterer);

            for (int i = 0; i < products.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/marketpage/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(products.get(i));
                if(column == 4){
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

    public void clearSearch() {
        priceMinTextField.clear();
        priceMaxTextField.clear();
        sortComboBox.setValue("ล่าสุด");
        sortByLatest();
        sortComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sortComboBox.getValue().equals("ล่าสุด")) {
                    sortByLatest();
                }
                else if (sortComboBox.getValue().equals("ราคาน้อยไปมาก")) {
                    sortByPriceMinToMax();
                }
                else if (sortComboBox.getValue().equals("ราคามากไปน้อย")) {
                    sortByPriceMaxToMin();
                }
            }
        });
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
}