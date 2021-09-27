package ku.cs.models.shop;

import ku.cs.models.user.User;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product searchByName(String name) {
        for (Product product: products) {
            if (product.isProductName(name)) {
                return product;
            }
        }
        return null;
    }

    public Product searchByID(String ID) {
        for (Product product: products) {
            if (product.isProductID(ID)) {
                return product;
            }
        }
        return null;
    }

    public Product getProduct(int i){
        return products.get(i);
    }

    public int count() {
        return this.products.size();
    }

    public void addNewProduct(String shopName, String name, double price, int remaining, String imageFilePath, String detail, int numRemainWarning) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter addedTime = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH:mm:ss");
        DateTimeFormatter productID = DateTimeFormatter.ofPattern("yyMMdd");
        Product item = new Product(addedTime.format(localDateTime),"2109260001",shopName,name,price,remaining,0.0,imageFilePath,detail,numRemainWarning);
        products.add(item);
    }

    public void editProduct(String id, String name, double price, int remaining, String imageFilePath, String detail){
        Product item = searchByID(id);
        if (item == null) { return; }
        item.setName(name);
        item.setPrice(price);
        item.setRemaining(remaining);
        item.setImageFilePath(imageFilePath);
        item.setDetail(detail);
    }

    public String toCsv() {
        String result = "";
        for (Product product: products) {
            result += product.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                '}';
    }
}
