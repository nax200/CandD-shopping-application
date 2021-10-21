package ku.cs.models.shop.promotion;

import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

public class Promotion {
    private String promotionName;
    private String promotionCode;
    private Customer shopOwner;
    private String useCondition;
    private double minimumAmount;
    // create promotion
    public Promotion(String promotionName, String promotionCode, Customer shopOwner, String useCondition, double minimumAmount) {
        this.promotionName = promotionName;
        this.promotionCode = promotionCode;
        this.shopOwner = shopOwner;
        this.useCondition = useCondition;
        this.minimumAmount = minimumAmount;
    }
    // read csv
    public Promotion(String promotionName, String promotionCode, String shopOwner, String useCondition, double minimumAmount){
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();
        this.promotionName = promotionName;
        this.promotionCode = promotionCode;
        this.shopOwner = (Customer)userList.searchUsername(shopOwner);
        this.useCondition = useCondition;
        this.minimumAmount = minimumAmount;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public Customer getShopOwner() {
        return shopOwner;
    }

    public Boolean isShopName(String shopName){
        return shopOwner.getUsername().equals(shopName);
    }

    public String getUseCondition(){return useCondition;}

    public double getMinimumAmount(){return minimumAmount;}

    public String getMinimumAmountString() {
        int amountInt = (int) minimumAmount;
        double amountDouble = minimumAmount;
        String amount;
        if(amountDouble-amountInt != 0.0)
        {
            amount = ""+ String.format("%.2f",amountDouble);
        }else{
            amount = ""+ amountInt;
        }
        return amount;
    }

    public String toCsv(){
        return getPromotionName()+","+getPromotionCode()+","+ getShopOwner().getUsername()+","+getUseCondition()+","+minimumAmount; // เขียนเก็บเป็น username
    }

}