import java.util.ArrayList;
import java.util.List;

public class SerowyKurczak extends Pizza {

    public SerowyKurczak(int idPizza, Size size) {
            super(idPizza, "Serowy kurczak", size, getDefaultIngredients(), 13.0f);
    }

    public static List<Ingredient> getDefaultIngredients(){
        List<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient("mozarella", 10.0f, TypeOfIngredients.CHEESE, 1));
        list.add(new Ingredient("sos serowy", 4.0f, TypeOfIngredients.SAUCE, 1));
        list.add(new Ingredient("kurczak", 11.0f, TypeOfIngredients.MEAT, 1));
        list.add(new Ingredient("papryka", 7.0f, TypeOfIngredients.VEGETABLES, 1)); // albo czosnek zamiast papryki
        list.add(new Ingredient("oregano", 1.0f, TypeOfIngredients.SEASONING, 3));
        return list;
    }
}
