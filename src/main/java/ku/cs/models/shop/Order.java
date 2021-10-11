package ku.cs.models.shop;

import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;
import ku.cs.services.UserFileDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private LocalDateTime addedTime;
    private String orderNo;
    private Customer buyer; // บันทึกเป็น username ของผู้ซื้อ
    private Product product;
    private int quantity;
    private String trackingNumber;
    private String address;


    // ตอนสร้าง order ใหม่
    public Order(String orderNo, Customer buyer, Product product, int quantity) {
        this.addedTime = LocalDateTime.now();
        this.orderNo = orderNo;
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
    }


    // ตอนอ่านจาก csv
    public Order(LocalDateTime addedTime, String orderNo, String buyer, String productID, int quantity,String trackingNumber, String address) {
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        DataSource<ProductList> dataSource2;
        dataSource2 = new ProductFileDataSource();
        ProductList productList = dataSource2.readData();

        this.addedTime = addedTime;
        this.orderNo = orderNo;
        this.buyer = (Customer) userList.searchUsername(buyer);
        this.product = productList.searchByID(productID);
        this.quantity = quantity;
        this.trackingNumber = trackingNumber;
        this.address = address;
    }
    
    // ------------- GETTER ----------------------

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    public String getAddedTimeToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dtf.format(addedTime);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Customer getBuyer() {
        return buyer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Double getTotalPrice(){
        return product.getPrice() * this.quantity;
    }

    public String getAddress(){
        return address;
    }

    // ------------- SETTER ----------------------

    public void setAddedTime(LocalDateTime addedTime) {
        this.addedTime = addedTime;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setBuyer(Customer buyer) {
        this.buyer = buyer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //--------- METHOD --------------

    public String toCsv(){
        return getAddedTimeToString() +","+ orderNo +","+ buyer.getUsername() + "," + product.getID() + "," + quantity + "," + trackingNumber + "," + address;
    }

}

