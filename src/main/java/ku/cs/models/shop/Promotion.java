package ku.cs.models.shop;

import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

public class Promotion {
    private String promotionName;
    private String promotionCode;
    private Customer promotionShopName;
    // create promotion
    public Promotion(String promotionName, String promotionCode, Customer promotionShopName) {
        this.promotionName = promotionName;
        this.promotionCode = promotionCode;
        this.promotionShopName = promotionShopName;
    }
    // read csv
    public Promotion(String promotionName, String promotionCode, String promotionShopName){
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        this.promotionName = promotionName;
        this.promotionCode = promotionCode;
        this.promotionShopName = (Customer)userList.searchUsername(promotionShopName);
    }

    public String getPromotionName() {
        return promotionName;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public Customer getPromotionShopName() {
        return promotionShopName;
    }

    public String toCsv(){
        return getPromotionName()+","+getPromotionCode()+","+getPromotionShopName().getUsername(); // เขียนเก็บเป็น username
    }

}
