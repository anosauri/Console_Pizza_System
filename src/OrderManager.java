import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class OrderManager {

    private Queue<Order> orderQueue;
    private Map<Integer, Order> allOrders;

    public OrderManager() {
        this.orderQueue = new LinkedList();
        this.allOrders = new HashMap<>();
    }

    public Order addOrder(Order order) {
        if(allOrders.containsKey(order.getIdOrder()))
            throw new IllegalArgumentException("Zamówienie " + order.getIdOrder() + " już istnieje");
        allOrders.put(order.getIdOrder(), order);
        orderQueue.offer(order);
        return order;
    }

    public void updateOrderStatus(int idOrder, OrderStatus newStatus) {
        Order o = allOrders.get(idOrder);
        if (o == null) {
            throw new NoSuchElementException("Nie znaleziono zamówienia o id " + idOrder);
        }
        if (o.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Nie można zmienić statusu anulowanego zamówienia.");
        }
        o.updateOrderStatus(newStatus);
    }


    public Order getOrderById(int idOrder) {
        Order o = allOrders.get(idOrder);
        if (o == null) {
            throw new NoSuchElementException("Nie znaleziono zamówienia o id " + idOrder);
        }
        return o;
    }

    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public Map<Integer, Order> getAllOrders() {
        return allOrders;
    }


}
