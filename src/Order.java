import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Order {
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    private int idOrder;
    private Client client;
    private LocalDateTime timeOfOrder;
    private LocalDateTime timeOfRealization;
    private OrderStatus status;
    private List<Pizza> pizzaList;
    private float totalPrice;
    private PromotionType promotion = PromotionType.NONE;
    private boolean scheduled = false;
    Random random = new Random();

    public Order(Client clientInformation) {
        if (clientInformation == null) {
            throw new IllegalArgumentException("Informacje o kliencie nie mogą być puste");
        }
        this.idOrder = random.nextInt(1000);
        this.pizzaList = new ArrayList<>();
        this.timeOfOrder = LocalDateTime.now();
        this.status = OrderStatus.ORDERING;
        this.client = clientInformation;
    }

    public void addPizza(Pizza pizza) {
        if (pizza != null) {
            pizzaList.add(pizza);
        } else {
            throw new IllegalArgumentException("Pizza nie może być pusta");
        }
    }


    public boolean removePizza(Pizza pizza) {
        return pizzaList.remove(pizza);
    }

    public void setPromotion(PromotionType promotion) {
        if (promotion == null)
            throw new IllegalArgumentException("Promocja nie może być pusta");
        this.promotion = promotion;
    }


    public float calculateTotalPrice() {
        totalPrice = 0;
        List<Float> prices = new ArrayList<>();

        for (Pizza pizza : pizzaList) {
            float price = pizza.calculatePrice();
            totalPrice += price;
            prices.add(price);
        }
        if (promotion == PromotionType.PERCENTAGE)
            totalPrice -= totalPrice * promotion.getDiscount();

        if (promotion == PromotionType.BUY_TWO_GET_ONE) {
            int pizzaCount = pizzaList.size() / 3;
            if (pizzaCount > 0) {
                prices.sort(Float::compare);
                for (int i = 0; i < pizzaCount; i++) {
                    totalPrice -= prices.get(i);
                }
            }
        }
        if (promotion == PromotionType.FREE_DELIVERY)
            totalPrice -= promotion.getDiscount();

        return totalPrice;
    }
    public int calculatePreparationTime() {
        int normalTime = 2;
        int pizzaTime = 0;
        for (Pizza pizza : pizzaList) {
            switch (pizza.getSizeName()) {
                case "mała":
                    pizzaTime += 1;
                    break;
                case "średnia":
                    pizzaTime += 2;
                    break;
                case "duża":
                    pizzaTime += 3;
                    break;
            }
            pizzaTime += normalTime;
        }
        return pizzaTime;
    }

    public LocalDateTime calculateReadyTime(LocalDateTime now) {
        int timeForPizza = calculatePreparationTime();
        return now.plusMinutes(timeForPizza);
    }

    public int getIdOrder() {
        return idOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void scheduleStatusUpdates() {
        int prepMinutes = calculatePreparationTime();
        System.out.println("Przewidywany czas przygotowania zamówienia: " + prepMinutes + " min");

        SCHEDULER.schedule(
                () -> updateOrderStatus(OrderStatus.IN_PREPARATION),
                1, TimeUnit.MINUTES
        );

        SCHEDULER.schedule(
                () -> {
                    updateOrderStatus(OrderStatus.READY);
                    timeOfRealization = LocalDateTime.now();
                },
                1 + prepMinutes, TimeUnit.MINUTES
        );
    }

    public synchronized void updateOrderStatus(OrderStatus newStatus) {
        if (newStatus == null)
            throw new IllegalArgumentException("Status zamówienia nie może być pusty");
        this.status = newStatus;
        if (newStatus == OrderStatus.READY)
            this.timeOfRealization = LocalDateTime.now();
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public PromotionType getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        return "\nOrder{" +
                "idOrder=" + idOrder +
                ", status=" + status +
                ", pizzaList=" + pizzaList +
                ", totalPrice=" + totalPrice +
                "} \n";
    }
}