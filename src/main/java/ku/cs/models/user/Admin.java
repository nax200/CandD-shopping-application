package ku.cs.models.user;

import java.time.LocalDateTime;

public class Admin extends User{

    public Admin(String username, String password, LocalDateTime lastTimeLoggedIn) {
        super(username, password,lastTimeLoggedIn);
    }

    @Override
    public String toCsv() {
        return "Admin," + getUsername()+ "," + getPassword() + "," + getLastTimeLoggedInToString();
    }
}
