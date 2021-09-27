package ku.cs.models.admin;

public class AdminUser {
    private String username;
    private String imgSrc;
    private String shopname;
    private String lastlogin;
    private boolean userBlocked;
    private int trytoLoginCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public boolean isUserBlocked() {
        return userBlocked;
    }

    public void setUserBlocked(boolean userBlocked) {
        this.userBlocked = userBlocked;
    }

    public String getIsUserBlockedString(){
        if(userBlocked == true){
            return "ถูกจำกัด";
        }
        return "ปกติ";
    }

    public int getTrytoLoginCount() {
        return trytoLoginCount;
    }

    public void setTrytoLoginCount(int trytoLoginCount) {
        this.trytoLoginCount = trytoLoginCount;
    }
}
