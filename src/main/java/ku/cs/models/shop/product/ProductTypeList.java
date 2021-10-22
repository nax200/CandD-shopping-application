package ku.cs.models.shop.product;

import java.util.ArrayList;

public class ProductTypeList {
    private ArrayList<ProductType> productTypes;

    public ProductTypeList() {
        productTypes = new ArrayList<>();
    }

    public void addProduct(ProductType productType) {
        this.productTypes.add(productType);
    }

    public int count() {
        return this.productTypes.size();
    }

    public String toCsv() {
        String result = "";
        for (ProductType productType: productTypes) {
            result += productType.toCsv() + "\n";
        }
        return result;
    }

    public ProductType getProductType(int i){
        return productTypes.get(i);
    }

    @Override
    public String toString() {
        return ""+productTypes;
    }

    public void addProduct(String type) {
        ProductType productType = new ProductType(productTypes.size(), type);
        productTypes.add(productType);
    }
}
