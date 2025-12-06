import java.util.ArrayList;
import java.util.List;

public class CustomPizza extends Pizza {

    public CustomPizza(int idPizza, Size size) {
        super(idPizza, "Custom", size, new ArrayList<>(), 20.0f);
    }

    public boolean addIngredient(String name) {
        Ingredient fromFridge = PizzaFactory.getIngredientFromFridge(name);

        if (fromFridge == null) {
            System.out.println("Nie ma takiego składnika w lodówce.");
            return false;
        }

        if (!canAddIngredient(fromFridge)) {
            System.out.println("Nie można dodać więcej składników (limit " + maxCountOfIngredients + ")");
            return false;
        }

        ingredientList.add(new Ingredient(fromFridge.getName(), fromFridge.getPrice(), fromFridge.getType(), 1));
        System.out.println("Dodano składnik: " + name);

        return true;
    }

    public boolean removeIngredient(String name) {
        for (int i = 0; i < ingredientList.size(); i++) {
            if (ingredientList.get(i).getName().equalsIgnoreCase(name)) {
                ingredientList.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }
    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public boolean isValidCustomPizza() {
        boolean hasSauce = false;
        boolean hasOtherIngredient = false;

        for (Ingredient ing : ingredientList) {
            if (ing.getType() == TypeOfIngredients.SAUCE) {
                hasSauce = true;
            } else {
                hasOtherIngredient = true;
            }

            // Jeśli oba warunki są spełnione, możemy przerwać pętlę
            if (hasSauce && hasOtherIngredient) {
                break;
            }
        }

        return hasSauce && hasOtherIngredient;
    }
}