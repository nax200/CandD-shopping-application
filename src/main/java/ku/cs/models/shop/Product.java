package ku.cs.models.shop;

public class Product {
    private String name;
    private int price;
    private String imageFilePath;

    public Product(){}

    public Product(String name, int price, String images){
        this.name = name;
        this.price = price;
        this.imageFilePath = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toCsv(){
        return "Product," + name + "," + price + "," + imageFilePath;
    }
}
