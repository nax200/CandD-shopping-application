package ku.cs.models.user;

import ku.cs.services.ConditionFilterer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserList {
    private ArrayList<User> users;

    public UserList(){ users = new ArrayList<>();}

    public User getUser(int i){
        return users.get(i);
    }

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

    public User searchByShopName(String shopName) {
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

    public void sortTime(Comparator<User> userComparator){
        Collections.sort(this.users,userComparator);
    }

    public ArrayList<User> filter(ConditionFilterer<User> filterer) {
        ArrayList<User> filtered = new ArrayList<>();
        for (User user: this.users) {
            if (filterer.match(user)) {
                filtered.add(user);
            }
        }
        return filtered;
    }

    public String toCsv() {
        String result = "";
        for (User user: users) {
            result += user.toCsv() + "\n";
        }
        return result;
    }

}
