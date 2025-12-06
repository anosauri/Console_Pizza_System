import java.util.ArrayList;
import java.util.List;

public class Farmerska extends Pizza {

    public Farmerska(int idPizza, Size size) {
        super(idPizza, "Farmerska", size, getDefaultIngredients(), 13.0f);
    }

    public static List<Ingredient> getDefaultIngredients(){
        List<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient("mozarella", 10.0f, TypeOfIngredients.CHEESE, 1));
        list.add(new Ingredient("sos pomidorowy", 2.0f, TypeOfIngredients.SAUCE, 1));
        list.add(new Ingredient("kurczak", 11.0f, TypeOfIngredients.MEAT, 1));
        list.add(new Ingredient("papryka",7.0f, TypeOfIngredients.VEGETABLES, 1));
        list.add(new Ingredient("cebula", 3.0f, TypeOfIngredients.VEGETABLES, 1));
        list.add(new Ingredient("oregano", 1.0f, TypeOfIngredients.SEASONING, 1));

        return list;
    }
}
