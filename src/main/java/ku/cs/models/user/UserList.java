package ku.cs.models.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList(){ users = new ArrayList<>();}

    public void addUser(User user){this.users.add(user);}

    public int count(){ return this.users.size();}

    public User searchUsername(String username) {
        for (User user: users) {
            if (user.isUsername(username)){
                return user;
            }
        }
        return null;
    }

    public boolean verifyLogin(String username, String password){
        for (User user: users){
            if (  username.equals(user.getUsername()) && password.equals(user.getPassword())  ){
                return true;
            }
        }
        return false;
    }

    public void setLastLogInTime(User user){
        user.setLastTimeLoggedIn(LocalDateTime.now());
    }


    public String toCsv() {
        String result = "";
        for (User user: users) {
            result += user.toCsv() + "\n";
        }
        return result;
    }

}
