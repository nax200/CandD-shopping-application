package ku.cs.models.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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

    public User searchShopName(String shopName) {
        for (User user: users) {
            if (user.getUsername().equals("admin")){
                continue;
            }
            if ( user.getShopName().equals(shopName) ){
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

    public void setLastLogInTime(User user) {
        user.setLastTimeLoggedIn(LocalDateTime.now());
    }

    public User getUser(int i){
        return users.get(i);
    }

    public void sortTime(Comparator<User> userComparator){
        Collections.sort(this.users,userComparator);
    }


    public String toCsv() {
        String result = "";
        for (User user: users) {
            result += user.toCsv() + "\n";
        }
        return result;
    }

}
