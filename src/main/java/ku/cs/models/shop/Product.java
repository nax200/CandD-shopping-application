package ku.cs.models.shop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {
    private LocalDateTime addedTime;
    private String ID;
    private String shopName;
    private String name;
    private double price;
    private int remaining;
    private double rating;
    private String imageFilePath;
    private String detail;
    private int numRemainWarning;
    public String type;

    public Product(String name, Double price, String ID){
        this.name = name;
        this.price = price;
        this.ID = ID;
    }

    public Product(LocalDateTime addedTime, String ID,String shopName, String name, double price, int remaining,
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

    public Product(LocalDateTime addedTime, String ID,String shopName, String name, double price, int remaining,
                   double rating, String imageFilePath, String detail, int numRemainWarning, String type)
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
        this.type = type;
    }

    public boolean isProductName(String name) {
        return this.name.equals(name);
    }

    public boolean isProductID(String ID) {
        return this.ID.equals(ID);
    }

//----------- GETTER ----------------


    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    public String getAddedTimeToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dtf.format(addedTime);
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

    public String getPriceString() {
        int priceInt = (int) getPrice();
        double priceDouble = getPrice();
        String price;
        if(priceDouble-priceInt != 0.0)
        {
            price = ""+ String.format("%.2f",priceDouble);
        }else{
            price = ""+ priceInt;
        }
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

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }

    //--------- METHOD --------------


    public String toCsv() {
        return getAddedTimeToString() +","+ ID +","+ shopName +","+ name +","+ price +","+ remaining +","+ rating +","+ getImageFilePath() +","+ detail +","+ numRemainWarning+","+type;
    }
}
