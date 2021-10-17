package ku.cs.models.shop;

public class ProductType {
    private String type;

    public ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toCsv() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}

