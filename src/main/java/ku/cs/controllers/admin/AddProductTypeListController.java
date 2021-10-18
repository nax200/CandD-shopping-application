package ku.cs.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.product.ProductType;
import ku.cs.models.shop.product.ProductTypeList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductTypeFileDataSource;

public class AddProductTypeListController{

    @FXML private TextField productTypeTextField;
    @FXML private AnchorPane parent;
    private ProductType productType;

    public void initialize() {
        ThemeController.setTheme(parent);
    }

    public void setData(ProductType productType){
        this.productType = productType;
        productTypeTextField.setText(productType.getType().trim());
    }


    @FXML public void editButton(ActionEvent event) {
        DataSource<ProductTypeList> dataSource;
        dataSource = new ProductTypeFileDataSource();
        ProductTypeList productTypeList = dataSource.readData();
        ProductType productType = productTypeList.getProductType(this.productType.getIndex());
        productType.setType(productTypeTextField.getText().trim());
        dataSource.writeData(productTypeList);
    }

}
