package ku.cs.models.shop.product;

public class ProductType {
    private int index;
    private String type;

    public ProductType(int index, String type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toCsv() {
        return index + "," + type;
    }

    @Override
    public String toString() {
        return type;
    }
}

