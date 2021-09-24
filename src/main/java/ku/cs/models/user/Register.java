package ku.cs.models.user;

import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

public class Register {
    public static boolean Register(String name, String username, String password, String cfPassword) {
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();

        // ตรวจดูว่ามีชื่อซ้ำไหม
        if( userList.searchUsername(username) != null ) {
            System.err.println("ชื่อซ้ำในระบบ");
            return false;
        }

        // เพิ่มบัญชีใหม่
        userList.addUser(new Customer("2109240001", username,password, "",name,false, "No-shop", "No-pic"));
        System.out.println("เพิ่มบัญชีใหม่แล้ว");
        dataSource.writeData(userList);
        return true;
    }

}
