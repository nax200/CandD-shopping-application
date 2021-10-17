package ku.cs.controllers.seller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ConfirmPopupController implements Initializable {

    @FXML private ImageView productImage;
    @FXML private TextField name;
    @FXML private TextField price;
    @FXML private TextField remain;
    @FXML private TextField warning;
    @FXML private TextField type;
    @FXML private TextArea des;
    @FXML private AnchorPane parent;

    private Product product;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void initData(Stage stage){
        this.stage = stage;
    }


    public void setData(Product product){
        this.product = product;
        name.setText(product.getName());
        price.setText(product.getPriceString());
        remain.setText(product.getRemaining()+"");
        warning.setText(product.getNumRemainWarning()+"");
        type.setText(product.getType());
        des.setText(product.getDetail());
        Image image = new Image("file:"+product.getImageFilePath(),true);
        productImage.setImage(image);
    }

    @FXML private void handleConfirmBtn(){
        ActionEvent a = new ActionEvent();
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();

        if (!product.getID().equals("")){
            Product editedProduct = productList.searchByID(product.getID());
            editedProduct.setName( product.getName() );
            editedProduct.setDetail( product.getDetail() );
            editedProduct.setPrice( product.getPrice() );
            editedProduct.setRemaining( product.getRemaining() );
            editedProduct.setNumRemainWarning( product.getNumRemainWarning() );
            editedProduct.setType(product.getType());
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images/productImage");
                File productImg = new File(product.getImageFilePath());
                if (!destDir.exists()) destDir.mkdirs();

                String filename = product.getID() + ".jpg";
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );
                Files.copy(productImg.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataSource.writeData(productList);
        }
        else {
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images/productImage");
                File productImg = new File(product.getImageFilePath());
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String filename = "P" + String.format("%05d", productList.count() + 1) + ".jpg";
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(productImg.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

                Product addProduct = new Product(LocalDateTime.now(), "P" + String.format("%05d", productList.count() + 1), LoginCustomer.customer.getShopName(),
                        product.getName(), product.getPrice(), product.getRemaining(), 0.0, destDir + "/" + filename, product.getDetail(), product.getNumRemainWarning(), product.getType());
                productList.addProduct(addProduct);
                dataSource.writeData(productList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        stage.close();
        try {
            com.github.saacsos.FXRouter.goTo("stock-total");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า stock-total ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleCancelBtn(){
        stage.close();
    }
}
