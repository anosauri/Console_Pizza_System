import java.util.ArrayList;
import java.util.List;

public class Vegetariana extends Pizza {

    public Vegetariana(int idPizza, Size size) {
        super(idPizza, "Vegetariana", size, getDefaultIngredients(), 14.0f);
    }

    public static List<Ingredient> getDefaultIngredients(){
        List<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient("mozarella", 10.0f, TypeOfIngredients.CHEESE, 1));
        list.add(new Ingredient("sos pomidorowy", 2.0f, TypeOfIngredients.SAUCE, 1));
        list.add(new Ingredient("ser feta", 9.0f, TypeOfIngredients.VEGETABLES, 1));
        list.add(new Ingredient("papryka",7.0f, TypeOfIngredients.VEGETABLES, 1));
        list.add(new Ingredient("cebula", 3.0f, TypeOfIngredients.VEGETABLES, 1));
        list.add(new Ingredient("oregano", 1.0f, TypeOfIngredients.SEASONING, 1));

        return list;
    }
}
