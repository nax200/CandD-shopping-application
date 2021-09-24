package ku.cs.models.shop;

import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toCsv() {
        String result = "";
        for (Product product: products) {
            result += product.toCsv() + "\n";
        }
        return result;
    }
}
