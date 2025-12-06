import java.util.ArrayList;
import java.util.List;

public class Margherita extends Pizza {

    public Margherita(int idPizza, Size size) {
        super( idPizza, "Margherita", size, getDefaultIngredients(), 13.0f);
    }

    public static List<Ingredient> getDefaultIngredients(){
        List<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient("mozarella", 10.0f, TypeOfIngredients.CHEESE, 2));
        list.add(new Ingredient("sos pomidorowy", 2.0f, TypeOfIngredients.SAUCE, 2));
        list.add(new Ingredient("oregano", 1.0f, TypeOfIngredients.SEASONING, 3));
        return list;
    }

}
