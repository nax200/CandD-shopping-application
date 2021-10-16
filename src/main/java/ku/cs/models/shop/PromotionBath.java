package ku.cs.models.shop;

import ku.cs.models.user.Customer;


public class PromotionBath extends Promotion{
    private int minimumAmount;
    private double bath;

    // create promotion
    public PromotionBath(String promotionName, String promotionCode, Customer promotionShopName, int minimumAmount, double bath) {
        super(promotionName, promotionCode, promotionShopName);
        this.minimumAmount = minimumAmount;
        this.bath = bath;
    }
    // read csv
    public PromotionBath(String promotionName, String promotionCode, String promotionShopName, int minimumAmount, double bath){
        super(promotionName,promotionCode,promotionShopName);
        this.minimumAmount = minimumAmount;
        this.bath = bath;

    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public double getBath() {
        return bath;
    }

    public double getDiscount(int quality){
        if(quality >= minimumAmount) {
            return bath;
        }
        return 0;
    }

    public double getCalculator(double purchase,int quality){
        if(purchase >= bath && quality >= minimumAmount){
            return  purchase - bath;
        }
        return purchase;
    }
    @Override
    public String toCsv() {
        return "PromotionBath" + super.toCsv()+","+getMinimumAmount()+","+getBath();
    }
}
