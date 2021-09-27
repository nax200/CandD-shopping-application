package ku.cs.models.user;

import java.time.LocalDateTime;

public class Customer extends User{
    private String userID;
    private String name;
    private boolean isBlocked;
    private String shopName;
    private String imageFilePath;

    public Customer(String userID, String username, String password, LocalDateTime lastTimeLoggedIn, String name, boolean isBlocked, String shopName, String imageFilePath) {
        super(username, password, lastTimeLoggedIn);
        this.userID = userID;
        this.name = name;
        this.isBlocked = isBlocked;
        this.shopName = shopName;
        this.imageFilePath = imageFilePath;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public String getShopName() {
        return shopName;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    @Override
    public String toCsv() {
        return "Customer," + userID + ","+ getUsername() + "," + getPassword() + "," + getLastTimeLoggedInToString() + ","
                + name + "," + isBlocked + "," + shopName + "," + imageFilePath;
    }
}
