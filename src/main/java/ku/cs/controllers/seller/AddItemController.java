package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ku.cs.models.shop.ProductList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;

import java.io.IOException;

public class AddItemController {
    @FXML
    private Button SaveAddimgButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea detailTextArea;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField remainingTextField;

    @FXML
    private TextField numRemainWarningTextField;

    @FXML
    private Button SaveAddDataButton;

    @FXML
    private void handleSaveAddDataButton(){
    String name = nameTextField.getText();
    String detail = detailTextArea.getText();
    String price = priceTextField.getText();
    String remaining = remainingTextField.getText();
    String numRemainWarning = numRemainWarningTextField.getText();

    DataSource<ProductList> dataSource;
    dataSource = new ProductFileDataSource();
    ProductList productList = dataSource.readData();

    productList.addNewProduct("shopzaza",name,price,remaining,"/images/marketpage/img_1.png",detail,numRemainWarning);
    dataSource.writeData(productList);
    nameTextField.clear();
    detailTextArea.clear();
    priceTextField.clear();
    remainingTextField.clear();
    numRemainWarningTextField.clear();
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
}
