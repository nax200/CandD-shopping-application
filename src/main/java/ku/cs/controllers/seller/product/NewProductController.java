package ku.cs.controllers.seller.product;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import ku.cs.models.shop.product.ProductTypeList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductTypeFileDataSource;
import javafx.scene.control.ComboBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewProductController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());

        DataSource<ProductTypeList> dataSource;
        dataSource = new ProductTypeFileDataSource();
        ProductTypeList productTypeList = dataSource.readData();
        String type = productTypeList.toString().replaceAll("\\[|\\]", "");
        String[] strings = type.split(", ");
        categoryComboBox.getItems().addAll(strings);
        categoryComboBox.setValue("");
    }

    @FXML
    private void handleSaveAddDataButton() throws IOException{
        if (    imageFile==null ||
                nameTextField.getText().trim().equals("") ||
                detailTextArea.getText().trim().equals("") ||
                priceTextField.getText().trim().equals("") ||
                remainingTextField.getText().trim().equals("") ||
                numRemainWarningTextField.getText().trim().equals("") || categoryComboBox.getValue().equals("")
        ){
            messageLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วนก่อนดำเนินการ");
            return;
        }

        try {
            String name = nameTextField.getText().trim();
            String detail = detailTextArea.getText().trim();
            double price = Double.parseDouble( priceTextField.getText().trim() );
            int remaining = Integer.parseInt( remainingTextField.getText().trim() );
            int numRemainWarning = Integer.parseInt( numRemainWarningTextField.getText().trim() );

            if (  price < 0 || remaining < 0 || numRemainWarning < 0 ) {
                messageLabel.setText("ไม่สามารถใส่ค่าเป็นจำนวนลบได้");
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ku/cs/sellerpage/product/confirm-popup.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            ConfirmPopupController confirmPopupController = fxmlLoader.getController();
            Product previewProduct = new Product("",name,price,remaining,numRemainWarning,categoryComboBox.getValue(),detail,imageFile.toPath()+"");
            confirmPopupController.setData(previewProduct);
            confirmPopupController.initData(stage);
            stage.showAndWait();
        } catch (IllegalArgumentException e){
            messageLabel.setText("โปรดใส่ประเภทข้อมูลให้ถูกต้อง");
        } catch (IOException e) {
            System.err.println("เปิดหน้าต่าง pop-up ไม่ได้");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancelButton(){
        nameTextField.clear();
        detailTextArea.clear();
        priceTextField.clear();
        remainingTextField.clear();
        numRemainWarningTextField.clear();
        productImage.setImage(null);
        messageLabel.setText("");
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

    @FXML
    void handleCreatePromotionButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("create-promotion");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า create-promotion ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleAllPromotionCreateButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("all-promotion-create");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า all-promotion-create ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
