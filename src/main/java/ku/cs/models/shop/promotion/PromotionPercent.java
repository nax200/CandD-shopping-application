package ku.cs.models.shop.promotion;

import ku.cs.models.user.Customer;

public class PromotionPercent extends Promotion {
    private double percent;

    // create promotion
    public PromotionPercent(String promotionName, String promotionCode, Customer shopName,String useCondition, double minimumAmount, double percent) {
        super(promotionName, promotionCode, shopName, useCondition, minimumAmount);
        this.percent = percent;
    }
    // read csv
    public PromotionPercent(String promotionName, String promotionCode, String shopName,String useCondition, double minimumAmount, double percent){
        super(promotionName,promotionCode,shopName,useCondition, minimumAmount);
        this.percent = percent;

    }

    public double getPercent() {
        return percent;
    }

    public String getPercentString() {
        int percentInt = (int) percent;
        double percentDouble = percent;
        String percent;
        if(percentDouble-percentInt != 0.0)
        {
            percent = ""+ String.format("%.2f",percentDouble);
        }else{
            percent = ""+ percentInt;
        }
        return percent;
    }

    public double calculatePriceSum(double purchase){
        if(percent>0 && percent<=100) {
            return purchase -((percent / 100) * purchase);
        }
        return  purchase;
    }

    @Override
    public String toCsv(){
        return "PromotionPercent," + super.toCsv() +","+getPercent();
    }
}
