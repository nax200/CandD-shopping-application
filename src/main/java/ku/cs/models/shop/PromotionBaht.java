package ku.cs.models.shop;

import ku.cs.models.user.Customer;


public class PromotionBaht extends Promotion{
    private int minimumAmount;
    private double baht;

    // create promotion
    public PromotionBaht(String promotionName, String promotionCode, Customer promotionShopName, int minimumAmount, double baht) {
        super(promotionName, promotionCode, promotionShopName);
        this.minimumAmount = minimumAmount;
        this.baht = baht;
    }
    // read csv
    public PromotionBaht(String promotionName, String promotionCode, String promotionShopName, int minimumAmount, double baht){
        super(promotionName,promotionCode,promotionShopName);
        this.minimumAmount = minimumAmount;
        this.baht = baht;

    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public double getBaht() {
        return baht;
    }

    public double getDiscount(int quality){
        if(quality >= minimumAmount) {
            return baht;
        }
        return 0;
    }

    public double getCalculator(double purchase,int quality){
        if(purchase >= baht && quality >= minimumAmount){
            return  purchase - baht;
        }
        return purchase;
    }
    @Override
    public String toCsv() {
        return "PromotionBaht" + super.toCsv()+","+getMinimumAmount()+","+ getBaht();
    }
}
