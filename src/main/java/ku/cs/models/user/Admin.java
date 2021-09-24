package ku.cs.models.user;

public class Admin extends User{

    public Admin(String username, String password, String lastTimeLoggedIn) {
        super(username, password,lastTimeLoggedIn);
    }

    @Override
    public String toCsv() {
        return "Admin," + getUsername()+ "," + getPassword() + "," + getLastTimeLoggedIn();
    }
}
