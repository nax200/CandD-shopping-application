package ku.cs.models.shop;

import ku.cs.services.ConditionFilterer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrderList {
    private ArrayList<Order> orders;

    public OrderList(){
        orders = new ArrayList<>();
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public ArrayList<Order> getOrders(){
        return orders;
    }

    public void sort(Comparator<Order> orderComparator){
        Collections.sort(this.orders, orderComparator);
    }
    public Order getOrder(int i){
        return orders.get(i);
    }

    public int count(){
        return this.orders.size();
    }

    public ArrayList<Order> filter(ConditionFilterer<Order> filterer) {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order order: this.orders) {
            if (filterer.match(order)) {
                filtered.add(order);
            }
        }
        return filtered;
    }

    public void editTrackingNumber(String orderNo,String trackingNumber){
        Order order = this.searchByOrderNo(orderNo);
        if (order == null) {return;}
        order.setTrackingNumber(trackingNumber);
    }
    

    public Order searchByOrderNo(String orderNo) {
        for (Order order: orders){
            if (order.getOrderNo().equals(orderNo))
                return order;
        }
        return null;
    }

    public Order searchByTrackingNumberIsEmoty(String trackingNumber){
        for (Order order: orders){
            if (!(order.getTrackingNumber().isEmpty()))
                return order;
        }
        return null;
    }



    public String toCsv(){
        String result = "";
        for (Order order: orders){
            result += order.toCsv() + "\n";
        }
        return result;
    }

}
