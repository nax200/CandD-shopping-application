package ku.cs.models.shop.promotion;

import ku.cs.models.user.Customer;

public class PromotionPercent extends Promotion {
    private double minimumPurchase;
    private double percent;

    // create promotion
    public PromotionPercent(String promotionName, String promotionCode, Customer shopName, double minimumPurchase, double percent) {
        super(promotionName, promotionCode, shopName);
        this.minimumPurchase = minimumPurchase;
        this.percent = percent;
    }
    // read csv
    public PromotionPercent(String promotionName, String promotionCode, String promotionShopName, double minimumPurchase, double Percent){
        super(promotionName,promotionCode,promotionShopName);
        this.minimumPurchase = minimumPurchase;
        this.percent = Percent;

    }

    public double getMinimumPurchase() {
        return minimumPurchase;
    }

    public double getPercent() {
        return percent;
    }

    public double getDiscount(double purchase){
        if(purchase >= minimumPurchase) {
            return (percent / 100) * purchase;
        }
        return 0;
    }

    public double getCalculator(double purchase){
        if(purchase >= minimumPurchase) {
            return purchase -((percent / 100) * purchase);
        }
        return  purchase;
    }
    @Override
    public String toCsv(){
        return "PromotionPercent" + super.toCsv() +","+getMinimumPurchase()+","+getPercent();
    }
}
