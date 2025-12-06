import java.util.ArrayList;
import java.util.List;

public class Serowa extends Pizza {

    public Serowa(int idPizza, Size size) {
        super(idPizza, "Serowa", size, getDefaultIngredients(), 13.0f);
    }

    public static List<Ingredient> getDefaultIngredients(){
        List<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient("mozarella", 10.0f, TypeOfIngredients.CHEESE, 2));
        list.add(new Ingredient("sos serowy", 4.0f, TypeOfIngredients.SAUCE, 1));
        list.add(new Ingredient("ser feta", 9.0f, TypeOfIngredients.CHEESE, 1));
        list.add(new Ingredient("oregano", 1.0f, TypeOfIngredients.SEASONING, 1));
        return list;
    }
}