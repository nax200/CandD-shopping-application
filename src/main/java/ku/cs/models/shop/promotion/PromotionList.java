package ku.cs.models.shop.promotion;

import ku.cs.models.shop.order.Order;
import ku.cs.models.user.Customer;
import ku.cs.services.ConditionFilterer;

import java.util.ArrayList;

public class PromotionList {
    private ArrayList<Promotion> promotions;

    public PromotionList(){promotions = new ArrayList<>();}

    public void addPromotion(Promotion promotion){this.promotions.add(promotion);}

    public int count() {return this.promotions.size();}

    public Promotion searchPromotion(String code){
        for (Promotion promotion : promotions){
            if(promotion.getPromotionCode().equalsIgnoreCase(code)){
                return promotion;
            }
        }
        return null;
    }

   public Customer searchUserName(String userName){
        for (Promotion promotion: promotions){
            if (promotion.getShopOwner().getShopName().equals(userName)){
                return promotion.getShopOwner();
            }
        }
       return null;
   }

    public ArrayList<Promotion> filter(ConditionFilterer<Promotion> filterer) {
        ArrayList<Promotion> filtered = new ArrayList<>();
        for (Promotion promotion: this.promotions) {
            if (filterer.match(promotion)) {
                filtered.add(promotion);
            }
        }
        return filtered;
    }

    public String toCsv(){
        String result = "";
        for (Promotion promotion: promotions){
            result += promotion.toCsv() +"\n";
        }
        return result;
    }

}
