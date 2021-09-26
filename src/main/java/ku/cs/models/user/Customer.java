package ku.cs.models.user;

public class Customer extends User{
    private String userID;
    private String name;
    private boolean isBlocked;
    private String shopName;
    private String imageFilePath;

    public Customer(String userID, String username, String password, String lastTimeLoggedIn, String name, boolean isBlocked, String shopName, String imageFilePath) {
        super(username, password, lastTimeLoggedIn);
        this.userID = userID;
        this.name = name;
        this.isBlocked = isBlocked;
        this.shopName = shopName;
        this.imageFilePath = imageFilePath;
    }

    public Customer(String username,String password,String lastTimeLoggedIn){
        super(username, password, lastTimeLoggedIn);
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isBlocked() {
        return isBlocked;
    }

    @Override
    public String getShopName() {
        return shopName;
    }

    @Override
    public String getIsUserBlockedToString(){
        if(isBlocked){
            return "ถูกจำกัด";
        }
        return "ปกติ";
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    @Override
    public String toCsv() {
        return "Customer," + userID + ","+ getUsername() + "," + getPassword() + "," + getLastTimeLoggedIn() + ","
                + name + "," + isBlocked + "," + shopName + "," + imageFilePath;
    }
}
