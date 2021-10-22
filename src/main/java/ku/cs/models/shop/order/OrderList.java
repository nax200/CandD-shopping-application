package ku.cs.models.shop.order;

import ku.cs.services.ConditionFilterer;

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

    public Order searchByOrderNo(String orderNo) {
        for (Order order: orders){
            if (order.getOrderNo().equals(orderNo))
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
