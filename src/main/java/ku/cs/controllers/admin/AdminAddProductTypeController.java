package ku.cs.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.product.ProductTypeList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductTypeFileDataSource;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminAddProductTypeController implements Initializable {
    @FXML private AnchorPane parent;
    @FXML private Label messageLabel;
    @FXML private TextField productTypeTextField;
    @FXML private VBox productTypeVBox;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        loadProductType();
    }

    @FXML
    public void goToAdminUserView(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-user-view");
        }catch (IOException e){
            System.err.println("ไปหน้า userList ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }

    @FXML
    public void logOut(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void confirmButton() {
        if(productTypeTextField.getText().trim().equals("")){
            messageLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }
        else {
            DataSource<ProductTypeList> dataSource;
            dataSource = new ProductTypeFileDataSource();
            ProductTypeList productTypeList = dataSource.readData();
            productTypeList.addProduct(productTypeTextField.getText());
            dataSource.writeData(productTypeList);
            productTypeTextField.clear();
            loadProductType();
        }
    }

    public void loadProductType() {
        productTypeVBox.getChildren().removeAll();
        productTypeVBox.getChildren().setAll();
        DataSource<ProductTypeList> dataSource;
        dataSource = new ProductTypeFileDataSource();
        ProductTypeList productTypeList = dataSource.readData();
        for (int i=0; i < productTypeList.count(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/add-product-type-list.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                AddProductTypeListController productTypeListController = fxmlLoader.getController();
                productTypeListController.setData(productTypeList.getProductType(i));
                productTypeVBox.getChildren().add(anchorPane);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML public void clickLogoGoToAdminUserView(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("admin-user-view");
        }catch (IOException e){
            System.err.println("ไปหน้า userList ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }
}
