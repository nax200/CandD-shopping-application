package ku.cs.models.shop;

import ku.cs.models.user.Customer;
import ku.cs.services.DataSource;
import ku.cs.services.OrderFileDataSource;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderListTest {
    @Test
    void testAddOrder() {
        DataSource<OrderList> dataSource;
        dataSource = new OrderFileDataSource();
        OrderList orderList = dataSource.readData();

        Customer customer = new Customer("moss", "1234");
        Product product = new Product("testProductName", 199.0, "P000001");

        Order order = new Order("R00001", customer, product, 4);
    }
}