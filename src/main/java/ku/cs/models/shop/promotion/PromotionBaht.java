package ku.cs.models.shop.promotion;

import ku.cs.models.user.Customer;


public class PromotionBaht extends Promotion{
    private double baht;

    // create promotion
    public PromotionBaht(String promotionName, String promotionCode, Customer promotionShopName,String useCondition, double minimumAmount, double baht) {
        super(promotionName, promotionCode, promotionShopName,useCondition,minimumAmount);
        this.baht = baht;
    }
    // read csv
    public PromotionBaht(String promotionName, String promotionCode, String promotionShopName,String useCondition, double minimumAmount, double baht){
        super(promotionName,promotionCode,promotionShopName,useCondition,minimumAmount);
        this.baht = baht;

    }

    public double getBaht() {
        return baht;
    }

    public String getBahtString() {
        int priceInt = (int) baht;
        double priceDouble = baht;
        String price;
        if(priceDouble-priceInt != 0.0)
        {
            price = ""+ String.format("%.2f",priceDouble);
        }else{
            price = ""+ priceInt;
        }
        return price;
    }

    public double getDiscount(double minimumAmount){
        if(super.getMinimumAmount() >= minimumAmount) {
            return baht;
        }
        return 0;
    }

    public double calculatePriceSum(double purchase){
        if(purchase - baht <= 0){
            return 0;
        }
        return  purchase - baht;
    }
    @Override
    public String toCsv() {
        return "PromotionBaht," + super.toCsv()+","+ getBaht();
    }
}
