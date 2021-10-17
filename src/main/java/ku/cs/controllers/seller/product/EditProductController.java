package ku.cs.controllers.seller.product;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.product.Product;
import ku.cs.models.user.LoginCustomer;
import ku.cs.models.shop.ProductTypeList;
import ku.cs.services.DataSource;
import javafx.scene.control.ComboBox;
import ku.cs.services.ProductTypeFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProductController implements Initializable {
    @FXML private TextField nameTextField;
    @FXML private TextArea detailTextArea;
    @FXML private TextField priceTextField;
    @FXML private TextField remainingTextField;
    @FXML private TextField numRemainWarningTextField;
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private ImageView productImage;
    @FXML private Label messageLabel;
    @FXML private AnchorPane parent;
    @FXML private ComboBox<String> categoryComboBox;

    private File imageFile;
    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
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
        categoryComboBox.setValue(product.getType());
        Image productImg = new Image("file:"+product.getImageFilePath(),true);
        imageFile = new File( product.getImageFilePath() );
        productImage.setImage(productImg);

        DataSource<ProductTypeList> dataSource;
        dataSource = new ProductTypeFileDataSource();
        ProductTypeList productTypeList = dataSource.readData();
        String type = productTypeList.toString().replaceAll("\\[|\\]", "");
        String[] strings = type.split(", ");
        categoryComboBox.getItems().addAll(strings);
    }

    @FXML
    private void handleSaveAddDataButton(){
        if (    imageFile==null ||
                nameTextField.getText().trim().equals("") ||
                detailTextArea.getText().trim().equals("") ||
                priceTextField.getText().trim().equals("") ||
                remainingTextField.getText().trim().equals("") ||
                numRemainWarningTextField.getText().trim().equals("")
        ){
            messageLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วนก่อนดำเนินการ");
            return;
        }
        String name = nameTextField.getText().trim();
        String detail = detailTextArea.getText().trim();
        double price = Double.parseDouble( priceTextField.getText().trim() );
        int remaining = Integer.parseInt( remainingTextField.getText().trim() );
        int numRemainWarning = Integer.parseInt( numRemainWarningTextField.getText().trim() );

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ku/cs/sellerpage/product/confirm-popup.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            ConfirmPopupController confirmPopupController = fxmlLoader.getController();
            Product previewProduct = new Product(product.getID(),name,price,remaining,numRemainWarning,categoryComboBox.getValue(),detail,imageFile.toPath()+"");
            confirmPopupController.setData(previewProduct);
            confirmPopupController.initData(stage);
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("เปิดหน้าต่าง pop-up ไม่ได้");
            e.printStackTrace();
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

        File imageFileFromChooser = fileChooser.showOpenDialog(null);

        if (imageFileFromChooser != null)
        {
            try {
                BufferedImage bufferedImage = ImageIO.read(imageFileFromChooser);
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                productImage.setImage(img);
                imageFile = imageFileFromChooser;
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
