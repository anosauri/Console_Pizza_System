public enum OrderStatus {
    ORDERING("Jesteś w trakcie składania zamówienia.", 0),
    ORDER_PLACED("Twoje zamówienie zostało złożone.", 1),
    IN_PREPARATION("Twoje zamówienie jest w trakcie przygotowania.",2),
    READY("Twoje zamówienie jest gotowe do odbioru.", 3),
    CANCELLED("Twoje zamówienie zostało anulowane.", 4);

    private final String  description;
    private final int priority;

    OrderStatus(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }
    public int getPriority() {
        return priority;
    }
}
