public enum PromotionType {
    NONE (0, "Brak promocji") ,
    PERCENTAGE(0.10f, "Promocja -10%"),
    BUY_TWO_GET_ONE (1.0f, "Promocja 2 + 1 gratis"),
    FREE_DELIVERY(10.0f, "Darmowa dostawa");

    private final float discount;
    private final String description;

    PromotionType(float discount, String description){
        this.discount = discount;
        this.description = description;
    }

    public float getDiscount() {
        return discount;
    }
    public String getDescription() {
        return description;
    }
}
