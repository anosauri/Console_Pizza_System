import java.util.ArrayList;
import java.util.List;

public class Pepperoni extends Pizza{
    public Pepperoni(int idPizza, Size size) {
        super(idPizza, "Pepperoni", size, getDefaultIngredients(), 13.0f);
    }

    public static List<Ingredient> getDefaultIngredients(){
        List<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient("mozarella", 10.0f, TypeOfIngredients.CHEESE, 2));
        list.add(new Ingredient("sos pomidorowy", 2.0f, TypeOfIngredients.SAUCE, 1));
        list.add(new Ingredient("pepperoni", 11.0f, TypeOfIngredients.MEAT, 1));
        list.add(new Ingredient("oregano", 1.0f, TypeOfIngredients.SEASONING, 1));
        return list;
    }
}
