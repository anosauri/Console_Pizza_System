import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {

    protected int idPizza;
    protected String name;
    protected Size size;
    protected List<Ingredient> ingredientList;
    protected float standardPrice;
    protected final int maxCountOfIngredients = 7;

    public Pizza(int idPizza, String name, Size size, List<Ingredient> ingredientList, float standardPrice) {
        this.idPizza = idPizza;
        this.name = name;
        this.size = size;
        this.ingredientList = ingredientList;
        this.standardPrice = standardPrice;
    }

    public float calculatePrice() {
        float allIngredientsPrice = 0;
        for (Ingredient ingredient : ingredientList) {
            allIngredientsPrice += ingredient.getPrice() * ingredient.getNumberOfIngredients();
        }
        return (allIngredientsPrice + standardPrice) * size.getPriceFactor();
    }

    public boolean canAddIngredient(Ingredient ingredient) {
        return ingredientList.size() < maxCountOfIngredients;
    }

    public String getSizeName() {
        switch (this.size) {
            case SMALL:
                return "mała";
            case MEDIUM:
                return "średnia";
            case LARGE:
                return "duża";
            default:
                return "nieznany rozmiar";
        }
    }

    public int getIdPizza() {
        return idPizza;
    }


    public String toString() {
        StringBuilder iList = new StringBuilder();
        for (Ingredient ingredient : ingredientList) {
            iList.append(ingredient.toString()).append(", ");
        }

        return "\nPizza: " + name + "\n"
                + "Składniki: " + iList + "\n"
                + "Rozmiar: " + getSizeName() + "\n"
                + "Cena: " + String.format("%.2f", calculatePrice()) + " zł";
    }
}
