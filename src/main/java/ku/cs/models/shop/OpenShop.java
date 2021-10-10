package ku.cs.models.shop;

import ku.cs.models.user.Customer;
import ku.cs.models.user.LoginCustomer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

public class OpenShop {
    public static boolean openShop(String shopName){
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();

        if( userList.searchShopName(shopName) != null ) {
            System.err.println("ชื่อร้านค้าซ้ำในระบบ");
            return false;
        }


        ((Customer) userList.searchUsername(LoginCustomer.customer.getUsername())).setShopName(shopName);
        dataSource.writeData(userList);
        return true;
    }
}
