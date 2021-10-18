package ku.cs.models.shop;

import ku.cs.models.shop.product.ProductTypeList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductTypeFileDataSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTypeListTest {
    @Test
    void testAddNewProductType(){
        DataSource<ProductTypeList> dataSource;
        dataSource = new ProductTypeFileDataSource();
        ProductTypeList productTypeList = dataSource.readData();
        productTypeList.addProduct("เสื้อผ้าแฟชั่น");
        dataSource.writeData(productTypeList);
        assertEquals(2, productTypeList.count());
    }
}