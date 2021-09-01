package ku.cs.models.user;

import java.io.File;
import java.util.Scanner;

public class User {
    private String name;
    private String username;
    private boolean loginStatus;


    public boolean verifyLogin(String username, String password){
        String nameFind = "";
        String usernameFind = "";
        String passwordFind = "";
        try {
            Scanner x = new Scanner(new File("src/main/resources/user/account.csv"));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                nameFind = x.next();
                usernameFind = x.next();
                passwordFind = x.next();
                if (username.equals(usernameFind) && password.equals(passwordFind)){
                    this.name = nameFind;
                    this.username = usernameFind;
                    this.loginStatus = true;
                    System.out.println("เข้าสู่ระบบสำเร็จ");
                    return true;
                }
            }
            System.out.println("ชื่อหรือรหัสผ่านไม่ถูกต้อง");
            return false;

        } catch (Exception e) {
            System.err.println("เกิดข้อผิดพลาด");
            return false;
        }
    }

}
