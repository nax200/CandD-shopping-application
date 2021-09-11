package ku.cs.models.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Register {
    public static boolean Register(String name, String username, String password, String cfpassword) {
        try {
            if (!password.equals(cfpassword)) {
                System.out.println("รหัสผ่านต่างกัน");
                return false;
            }
            if (password.equals("") || username.equals("") || name.equals("")){
                System.out.println("ใส่ข้อมูลบางอย่างไม่ครบ");
                return false;
            }
            FileWriter fw = new FileWriter("src/main/resources/user/account.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(name+","+username+","+password);
            pw.flush();
            pw.close();

            System.out.println("สมัครสมาชิกสำเร็จ");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkSameUsername(String username) {
        //boolean found = false;
        String usernameFind = "";
        try {
            Scanner x = new Scanner(new File("src/main/resources/user/account.csv"));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                x.next();
                usernameFind = x.next();
                x.next();
                if (username.equals(usernameFind)){
                    System.out.println("Username ซ้ำกันในระบบ");
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            System.err.println("เกิดข้อผิดพลาดในการตรวจสอบชื่อบัญชี");
            return false;
        }
    }
}
