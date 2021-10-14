package ku.cs.models.shop;

import ku.cs.models.user.Customer;
import ku.cs.models.user.LoginCustomer;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

public class OpenShop {
    public static boolean openShop(String shopName){
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();

        if( userList.searchByShopName(shopName) != null ) {
            System.err.println("ชื่อร้านค้าซ้ำในระบบ");
            return false;
        }

        Customer editedCustomer = (Customer) userList.searchUsername( LoginCustomer.customer.getUsername() );
        editedCustomer.setShopName(shopName);
        LoginCustomer.setCustomer(editedCustomer);
        dataSource.writeData(userList);
        return true;
    }
}
