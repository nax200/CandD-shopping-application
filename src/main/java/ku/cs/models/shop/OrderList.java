package ku.cs.models.shop;

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

    public ArrayList<Order> filter(ConditionFilterer<Order> filterer) {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order order: this.orders) {
            if (filterer.match(order)) {
                filtered.add(order);
            }
        }
        return filtered;
    }

    public Order searchByShopName(String shopName) {
        for (Order order: orders) {
            if (order.isShopName(shopName)) {
                return order;
            }
        }
        return null;
    }

    public Order searchByOrderCode(String orderCode){
        for (Order order: orders){
            if (order.isOrderCode(orderCode)){
                return order;
            }
        }
        return null;
    }

    public Order getOrder(int i){
        return orders.get(i);
    }

    public int count(){
        return this.orders.size();
    }

//    public void addNewOrder(String orderCode, String shopName, String name, double price,String username,
//                            String nameProduct, int remaining, String status, String trackingNumber){
//        Order orderProduct = new Order(orderCode, shopName, name, price,username,
//                            nameProduct, remaining, status, trackingNumber  );
//        orders.add(orderProduct);
//    }

    public void addNewOrder(Order order){
        orders.add(order);
    }


    public void editOrderTrackingNumber(String orderCode,String trackingNumber){
        Order orderProduct = searchByOrderCode(orderCode);
        if (orderProduct == null) {return;}
        orderProduct.setTrackingNumber(trackingNumber);

    }
    public String toCsv(){
        String result = "";
        for (Order order: orders){
            result += order.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + orders +
                '}';
    }

}//end
