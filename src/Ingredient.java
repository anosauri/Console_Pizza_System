public class Ingredient {
    private String name;
    private float price;

    private TypeOfIngredients type;
    private int numberOfIngredients;

    public Ingredient(String name, float price, TypeOfIngredients type, int numberOfIngredients) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.numberOfIngredients = numberOfIngredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0) {
            throw new IllegalArgumentException("Cena nie może być ujemna: " + price);
        }
        this.price = price;
    }

    public TypeOfIngredients getType() {
        return type;
    }


    public int getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public void setNumberOfIngredients(int numberOfIngredients) {
        if (numberOfIngredients < 0) {
            throw new IllegalArgumentException("Liczba składników nie może być ujemna");
        }
        this.numberOfIngredients = numberOfIngredients;
    }

    public void decreaseIngredientsNumber(int amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Liczba musi być dodatnia");
        }
        if(this.numberOfIngredients < amount){
            throw new IllegalArgumentException("Za mało składników w lodówce");
        }
        this.numberOfIngredients -= amount;
    }

    public String toString(){
        return name;
    }

}
