package ku.cs.models.user;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    private String username;
    private String password;
    private LocalDateTime lastTimeLoggedIn;

    public User(String username, String password,LocalDateTime lastTimeLoggedIn) {
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
    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public String getLastTimeLoggedInToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dtf.format(lastTimeLoggedIn);
    }

    public String getShopName(){return "-";}

    public boolean isBlocked(){return false;}

    public String getIsUserBlockedToString(){return "ปกติ";}


    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastTimeLoggedIn(LocalDateTime time){ lastTimeLoggedIn = time; }

}
