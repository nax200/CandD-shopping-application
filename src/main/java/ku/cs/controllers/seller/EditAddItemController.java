package ku.cs.controllers.seller;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

public class EditAddItemController implements Initializable {
    @FXML private Button SaveAddimgButton;
    @FXML private Button cancelButton;
    @FXML private TextField nameTextField;
    @FXML private TextArea detailTextArea;
    @FXML private TextField priceTextField;
    @FXML private TextField remainingTextField;
    @FXML private TextField numRemainWarningTextField;
    @FXML private Button SaveAddDataButton;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private ImageView productImage;

    private File imageFile;
    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Title-bar
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());

        // ------------------------------
        product = (Product) com.github.saacsos.FXRouter.getData();
        nameTextField.setText( product.getName() );
        detailTextArea.setText( product.getDetail() );
        priceTextField.setText( product.getPriceString() );
        remainingTextField.setText( ""+product.getRemaining() );
        numRemainWarningTextField.setText( ""+product.getNumRemainWarning() );
        Image productImg = new Image("file:"+product.getImageFilePath(),true);
        productImage.setImage(productImg);
    }

    @FXML
    private void handleSaveAddDataButton(){
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();

        Product editedProduct = productList.searchByID(product.getID());
        editedProduct.setName(nameTextField.getText().trim());
        editedProduct.setDetail(detailTextArea.getText().trim());
        editedProduct.setPrice( Double.parseDouble(priceTextField.getText().trim()) );
        editedProduct.setRemaining( Integer.parseInt(remainingTextField.getText().trim() ) );
        editedProduct.setNumRemainWarning( Integer.parseInt( numRemainWarningTextField.getText().trim() ) );

        if (imageFile != null) {
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images/productImage");
                if (!destDir.exists()) destDir.mkdirs();

                String filename = product.getID() + ".jpg";
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );
                Files.copy(imageFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                dataSource.writeData(productList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dataSource.writeData(productList);
        try {
            com.github.saacsos.FXRouter.goTo("stock-total");
        } catch (IOException e) {
            System.err.println("กลับไปหน้าหลักไม่ได้");
        }
    }

    @FXML
    public void browseImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("เลือกรูปภาพ...");

        // ใช้ filter เพื่อกรองเอาแต่ไฟล์ jpg และ png
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        fileChooser.setInitialDirectory(userDirectory);

        imageFile = fileChooser.showOpenDialog(null);

        if (imageFile != null)
        {
            try {
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                productImage.setImage(img);
            } catch (Exception e) {
                System.err.println("เกิดปัญหาในการเลือกไฟล์");
            }
        }
    }

    @FXML
    public void handleLowStockButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("low-stock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า low-stock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleNewOrderButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("new-order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า new-order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleShippedOrderButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("shipped-order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shipped-order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleStockTotalButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("stock-total");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า stock-total ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("add-item");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add-item ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleEditShopButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit-shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    void goToEditProfile(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToOpenShop(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
