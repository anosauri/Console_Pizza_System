import java.util.*;

public class PizzaFactory {
    private static int idCounter = 1;
    private static Map<String, Ingredient> mapa = new HashMap<>();

    static {
        mapa.put("mozarella", new Ingredient("mozarella", 10.0f, TypeOfIngredients.CHEESE, 500));
        mapa.put("sos pomidorowy", new Ingredient("sos pomidorowy", 2.0f, TypeOfIngredients.SAUCE, 500));
        mapa.put("pepperoni", new Ingredient("pepperoni", 11.0f, TypeOfIngredients.MEAT, 500));
        mapa.put("czosnek", new Ingredient("czosnek", 1.0f, TypeOfIngredients.VEGETABLES, 500));
        mapa.put("oregano", new Ingredient("oregano", 1.0f, TypeOfIngredients.SEASONING, 500));
        mapa.put("papryka", new Ingredient("papryka", 7.0f, TypeOfIngredients.VEGETABLES, 500));
        mapa.put("ser feta", new Ingredient("ser feta", 9.0f, TypeOfIngredients.CHEESE, 500));
        mapa.put("sos serowy", new Ingredient("sos serowy", 4.0f, TypeOfIngredients.SAUCE, 500));
        mapa.put("cebula", new Ingredient("cebula", 3.0f, TypeOfIngredients.VEGETABLES, 500));
        mapa.put("kurczak", new Ingredient("kurczak", 11.0f, TypeOfIngredients.MEAT, 500));
    }


    public static Pizza createMargherita(Size size) {
        List<Ingredient> requiredIngredients = Margherita.getDefaultIngredients();
        usingIngredients(requiredIngredients);
        return new Margherita(idCounter++, size);
    }
    public static Pizza createPepperoni(Size size) {
        List<Ingredient> requiredIngredients = Pepperoni.getDefaultIngredients();
        usingIngredients(requiredIngredients);
        return new Pepperoni(idCounter++, size);
    }

    public static Pizza createVegetariana(Size size) {
        List<Ingredient> requiredIngredients = Vegetariana.getDefaultIngredients();
        usingIngredients(requiredIngredients);
        return new Vegetariana(idCounter++, size);
    }

    public static Pizza createSerowa(Size size) {
        List<Ingredient> requiredIngredients = Serowa.getDefaultIngredients();
        usingIngredients(requiredIngredients);
        return new Serowa(idCounter++, size);
    }

    public static Pizza createFarmerska(Size size){
        List<Ingredient> requiredIngredients = Farmerska.getDefaultIngredients();
        usingIngredients(requiredIngredients);
        return new Farmerska(idCounter++, size);
    }

    public static Pizza createSerowyKurczak(Size size){
        List<Ingredient> requiredIngredients = SerowyKurczak.getDefaultIngredients();
        usingIngredients(requiredIngredients);
        return new SerowyKurczak(idCounter++, size);
    }


    private static void usingIngredients(List<Ingredient> requiredIngredients) {
        for (Ingredient required : requiredIngredients) {
            Ingredient available = mapa.get(required.getName());

            if (available == null || available.getNumberOfIngredients() < required.getNumberOfIngredients()) {
                throw new IllegalArgumentException("Brak wystarczającej ilości składnika: " + required.getName());
            }

            available.decreaseIngredientsNumber(required.getNumberOfIngredients());
        }
    }

    public static Ingredient getIngredientFromFridge(String name) {
        Ingredient ingredient = mapa.get(name.toLowerCase());
        if (ingredient == null)
            throw new NoSuchElementException("Nie znaleziono składnika: " + name);
        return new Ingredient(ingredient.getName(), ingredient.getPrice(), ingredient.getType(), ingredient.getNumberOfIngredients());
    }

    public static Pizza createCustomPizza(Size size, List<Ingredient> customIngredients) {
        usingIngredients(customIngredients);
        CustomPizza pizza = new CustomPizza(idCounter++, size);
        pizza.setIngredientList(new ArrayList<>(customIngredients));
        return pizza;
    }

    public static void displayAvailableIngredients() {
        System.out.println("Dostępne składniki:");
        for (Ingredient ingredient : mapa.values()) {
            System.out.println("- " + ingredient.getName() + " (" + ingredient.getType() + ")");
        }
        System.out.println("-----------");
    }

    public static List<Pizza> getMenu() {
        return List.of(
                createMargherita(Size.SMALL),
                createMargherita(Size.MEDIUM),
                createMargherita(Size.LARGE),
                createPepperoni(Size.SMALL),
                createPepperoni(Size.MEDIUM),
                createPepperoni(Size.LARGE),
                createVegetariana(Size.SMALL),
                createVegetariana(Size.MEDIUM),
                createVegetariana(Size.LARGE),
                createSerowa(Size.SMALL),
                createSerowa(Size.MEDIUM),
                createSerowa(Size.LARGE),
                createFarmerska(Size.SMALL),
                createFarmerska(Size.MEDIUM),
                createFarmerska(Size.LARGE),
                createSerowyKurczak(Size.SMALL),
                createSerowyKurczak(Size.MEDIUM),
                createSerowyKurczak(Size.LARGE)
        );
    }

    public static void displayMenu() {
        System.out.println("=== MENU ===");
        List<Pizza> menu = getMenu();
        for (Pizza pizza : menu) {
            System.out.println(pizza.toString());
            System.out.println("------------");
        }
        System.out.println("Pizza: Własna kompozycja");
        System.out.println("Składniki: dowolne składniki (maksymalnie 7)");
        System.out.println("Rozmiar: mała/średnia/duża");
        System.out.println("Cena: uzależniona od wybranych składników");
        System.out.println("------------");
    }
}
