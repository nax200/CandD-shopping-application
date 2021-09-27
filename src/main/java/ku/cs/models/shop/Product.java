package ku.cs.models.shop;

public class Product {
    private String addedTime;
    private String ID;
    private String shopName;
    private String name;
    private double price;
    private int remaining;
    private double rating;
    private String imageFilePath;
    private String detail;
    private int numRemainWarning;
    //public String type; กรณีถ้าจะทำเพิ่ม

    public Product(){}

    public Product(String addedTime, String ID, String shopName, String name, double price, int remaining,
                   double rating, String imageFilePath, String detail, int numRemainWarning)
    {
        this.addedTime = addedTime;
        this.ID = ID;
        this.shopName = shopName;
        this.name = name;
        this.price = price;
        this.remaining = remaining;
        this.rating = rating;
        this.imageFilePath = imageFilePath;
        this.detail = detail;
        this.numRemainWarning = numRemainWarning;
    }

    public boolean isProductName(String name) {
        return this.name.equals(name);
    }

    public boolean isProductID(String ID) {
        return this.ID.equals(ID);
    }

//----------- GETTER ----------------


    public String getAddedTime() {
        return addedTime;
    }

    public String getID() {
        return ID;
    }

    public String getShopName() {
        return shopName;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getRemaining() {
        return remaining;
    }

    public double getRating() {
        return rating;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public String getDetail() {
        return detail;
    }

    public int getNumRemainWarning() {
        return numRemainWarning;
    }

    //------------- SETTER --------------------


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setNumRemainWarning(int numRemainWarning) {
        this.numRemainWarning = numRemainWarning;
    }

    //--------- METHOD --------------


    public String toCsv() {
        return addedTime +","+ ID +","+ shopName +","+ name +","+ price +","+ remaining +","+ rating +","+ imageFilePath +","+ detail +","+ numRemainWarning;
    }
}
