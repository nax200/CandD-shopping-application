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
    @FXML private TextField searchTextField;
    @FXML private Label messageLabel;
    @FXML private Button searchProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortByLatest();
        sortComboBox.getItems().addAll("ล่าสุด","ราคาน้อยไปมาก", "ราคามากไปน้อย");
        categoryComboBox.getItems().addAll("เครื่องแต่งกาย", "เสื้อผ้าแฟชั่น");
        categoryComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedType();
            }
        });
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
        searchProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listProduct.getChildren().removeAll();
                listProduct.getChildren().setAll();
                searchProduct();
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

    public void searchByPrice() {
        listProduct.getChildren().removeAll();
        listProduct.getChildren().setAll();
        sortComboBox.setValue("ล่าสุด");
        messageLabel.setText("");
        if (priceMinTextField.getText().equals("") || priceMaxTextField.getText().equals("")) {
            messageLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วน");
        }
        else {
            double priceMin;
            double priceMax;
            try {
                String priceMinText = priceMinTextField.getText();
                priceMin = Double.parseDouble(priceMinText);
                String priceMaxText = priceMaxTextField.getText();
                priceMax = Double.parseDouble(priceMaxText);
            } catch (NumberFormatException e) {
                messageLabel.setText("ใส่ข้อมูลไม่ถูกต้อง");
                return;
            }
            ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
                @Override
                public boolean match(Product product) {
                    return product.getPrice() >= priceMin && product.getPrice() <= priceMax;
                }
            };
            if (priceMin >= 0 && priceMax > 0 && priceMax >= priceMin) {
                sortByLatest(filterer);
                categoryComboBox.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        listProduct.getChildren().removeAll();
                        listProduct.getChildren().setAll();
                        messageLabel.setText("");
                        ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
                            @Override
                            public boolean match(Product product) {
                                return product.getPrice() >= Double.parseDouble(priceMinTextField.getText()) && product.getPrice() <= Double.parseDouble(priceMaxTextField.getText()) && product.getType().equals(categoryComboBox.getValue());
                            }
                        };
                        searchByPrice(filterer);
                    }
                });
            }
        }
    }

    public void searchByPrice(ConditionFilterer<Product> filterer) {
        listProduct.getChildren().removeAll();
        listProduct.getChildren().setAll();
        sortComboBox.setValue("ล่าสุด");
        messageLabel.setText("");
        categoryLabel.setText(categoryComboBox.getValue());
        if (priceMinTextField.getText().equals("") || priceMaxTextField.getText().equals("")) {
            messageLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วน");
        }
        else {
            double priceMin;
            double priceMax;
            try {
                String priceMinText = priceMinTextField.getText();
                priceMin = Double.parseDouble(priceMinText);
                String priceMaxText = priceMaxTextField.getText();
                priceMax = Double.parseDouble(priceMaxText);
            } catch (NumberFormatException e) {
                messageLabel.setText("ใส่ข้อมูลไม่ถูกต้อง");
                return;
            }
            if (priceMin >= 0 && priceMax > 0 && priceMax >= priceMin) {
                listProduct.getChildren().removeAll();
                listProduct.getChildren().setAll();
                sortByLatest(filterer);
            }
        }
    }

    public void searchProduct(){
        ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
            @Override
            public boolean match(Product product) {
                return product.getName().equals(searchTextField.getText());
            }
        };
        sortComboBox.setValue("ล่าสุด");
        listProduct.getChildren().removeAll();
        listProduct.getChildren().setAll();
        priceMaxTextField.clear();
        priceMinTextField.clear();
        messageLabel.setText("");
        sortByLatest(filterer);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listProduct.getChildren().removeAll();
                listProduct.getChildren().setAll();
                messageLabel.setText("");
                ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
                    @Override
                    public boolean match(Product product) {
                        return product.getPrice() >= Double.parseDouble(priceMinTextField.getText()) && product.getPrice() <= Double.parseDouble(priceMaxTextField.getText()) && product.getName().equals(searchTextField.getText());
                    }
                };
                searchByPrice(filterer);
            }
        });
    }

    public void selectedType(){
        categoryLabel.setText(categoryComboBox.getValue());
        listProduct.getChildren().removeAll();
        listProduct.getChildren().setAll();
        priceMaxTextField.clear();
        priceMinTextField.clear();
        messageLabel.setText("");
        sortComboBox.setValue("ล่าสุด");
        searchTextField.clear();
        ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
            @Override
            public boolean match(Product product) {
                return product.getType().equals(categoryComboBox.getValue());
            }
        };
        sortByLatest(filterer);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listProduct.getChildren().removeAll();
                listProduct.getChildren().setAll();
                messageLabel.setText("");
                ConditionFilterer<Product> filterer = new ConditionFilterer<Product>() {
                    @Override
                    public boolean match(Product product) {
                        return product.getPrice() >= Double.parseDouble(priceMinTextField.getText()) && product.getPrice() <= Double.parseDouble(priceMaxTextField.getText()) && product.getType().equals(categoryComboBox.getValue());
                    }
                };
                searchByPrice(filterer);
            }
        });
    }

    public void clearSearch() {
        priceMinTextField.clear();
        priceMaxTextField.clear();
        searchTextField.clear();
        sortComboBox.setValue("ล่าสุด");
        categoryComboBox.setValue("");
        categoryLabel.setText("ทั้งหมด");
        categoryComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedType();
            }
        });
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
        searchProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listProduct.getChildren().removeAll();
                listProduct.getChildren().setAll();
                searchProduct();
            }
        });
        messageLabel.setText("");
        sortByLatest();
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
}