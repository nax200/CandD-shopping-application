package ku.cs.models.shop.product;

import ku.cs.services.ConditionFilterer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void sort(Comparator<Product> productComparator)
    {
        Collections.sort(this.products, productComparator);
    }

    public ArrayList<Product> filter(ConditionFilterer<Product> filterer) {
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product product: this.products) {
            if (filterer.match(product)) {
                filtered.add(product);
            }
        }
        return filtered;
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
