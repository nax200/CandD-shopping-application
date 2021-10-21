package ku.cs.models.user;

import java.time.LocalDateTime;

public class Admin extends User{

    // ------------ CONSTRUCTOR ------------------

    public Admin(String username, String password, LocalDateTime lastTimeLoggedIn) {
        super(username, password,lastTimeLoggedIn);
    }

    // ------------ METHODS ------------------

    @Override
    public String toCsv() {
        return "Admin," + getUsername()+ "," + getPassword() + "," + getLastTimeLoggedInToString();
    }
}
