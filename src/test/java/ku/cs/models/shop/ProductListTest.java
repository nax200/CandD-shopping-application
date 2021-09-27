package ku.cs.models.shop;

import ku.cs.models.user.User;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ProductListTest {

    @Test
    void testAddNewProduct(){
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();

        productList.addNewProduct("shopzaza","เสื้อแฟชั่น","199","10","/images/marketpage/img_1.png","เส้ือลายฮิตสุดคุ้ม","3");
        dataSource.writeData(productList);
        assertEquals("shopzaza",productList.searchByName("เสื้อแฟชั่น").getShopName());
        assertEquals("เสื้อแฟชั่น",productList.searchByName("เสื้อแฟชั่น").getName());
        assertEquals(199,productList.searchByName("เสื้อแฟชั่น").getPrice());
        assertEquals(10,productList.searchByName("เสื้อแฟชั่น").getRemaining());
        assertEquals("/images/marketpage/img_1.png",productList.searchByName("เสื้อแฟชั่น").getImageFilePath());
        assertEquals("เส้ือลายฮิตสุดคุ้ม",productList.searchByName("เสื้อแฟชั่น").getDetail());
        assertEquals(3,productList.searchByName("เสื้อแฟชั่น").getNumRemainWarning());
    }

    @Test
    void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = new User("nax","1234",LocalDateTime.now());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
        System.out.println(dtf.format(localDateTime));
        System.out.println(user.getLastTimeLoggedIn());
        System.out.println(user.getLastTimeLoggedInToString());

    }



}