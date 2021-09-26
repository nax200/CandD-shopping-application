package ku.cs.models.user;

public class User {
    private String username;
    private String password;
    private String lastTimeLoggedIn;

    public User(String username, String password,String lastTimeLoggedIn) {
        this.username = username;
        this.password = password;
        this.lastTimeLoggedIn = lastTimeLoggedIn;

    }

    public String toCsv() {
        return "User," + username + "," +password + "," + lastTimeLoggedIn;
   }

    public boolean isUsername(String Username) {
        return this.username.equals(Username);
    }

    public String getUsername(){
        return username;
   }

    public String getPassword(){
        return password;
   }

    public String getLastTimeLoggedIn() { return lastTimeLoggedIn; }

    public String getShopName(){return "-";}

    public boolean isBlocked(){return false;}

    public String getIsUserBlockedToString(){return "ปกติ";}


    public void setLastTimeLoggedIn(String time){ lastTimeLoggedIn = time; }

}
