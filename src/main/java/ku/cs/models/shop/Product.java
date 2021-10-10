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
    private int quantity;
    //public String type; กรณีถ้าจะทำเพิ่ม

    public Product(){

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

//    public Product(String addedTime,String ID,String shopName,String name,String price,String remaining ,String rating,String imageFilePath,String detail,String numRemainWarning) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        this.addedTime = LocalDateTime.parse(addedTime,dtf);
//        this.ID = ID;
//        this.shopName = shopName;
//        this.name = name;
//        this.price = Double.parseDouble(price);
//        this.remaining = Integer.parseInt(remaining);
//        this.rating = Double.parseDouble(rating);
//        this.imageFilePath = imageFilePath;
//        this.detail = detail;
//        this.numRemainWarning  = Integer.parseInt(numRemainWarning);
//        //addedTime.format(localDateTime),"2109260001",shopName,name,price,remaining,0.0,imageFilePath,detail,numRemainWarning
//    }

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

    public int getQuantity() {
        return quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //--------- METHOD --------------


    public String toCsv() {
        return getAddedTimeToString() +","+ ID +","+ shopName +","+ name +","+ price +","+ remaining +","+ rating +","+ getImageFilePath() +","+ detail +","+ numRemainWarning;
    }
}
