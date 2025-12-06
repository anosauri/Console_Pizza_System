public enum Size {
    SMALL(20, 0.8f),
    MEDIUM(30, 1.0f),
    LARGE(40, 1.5f);

    private final int diameter;
    private final float priceFactor;

    Size(int diameter, float priceFactor) {
        this.diameter = diameter;
        this.priceFactor = priceFactor;
    }

    public int getDiameter() {
        return diameter;
    }

    public float getPriceFactor() {
        return priceFactor;
    }

}
