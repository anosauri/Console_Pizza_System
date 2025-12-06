import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PizzeriaSystem {

    private OrderManager orderManager;
    private Order currentOrder;
    private Client currentClient;
    private Scanner scanner;
    public int start = 9;
    private static int clientCounter = 0;

    public PizzeriaSystem() {
        this.orderManager = new OrderManager();
        this.currentOrder = null;
        this.currentClient = null;
        this.scanner = new Scanner(System.in);
    }

    public void runSystem() {


        System.out.println("Witamy w Pizzerii :)");
        while (start != 0) {
            try {

                System.out.println("1 - Wyświetl menu.");
                System.out.println("2 - Złóż nowe zamówienie (podaj dane)");
                System.out.println("3 - Dodaj pizzę do bieżącego zamówienia");
                System.out.println("4 - Usuń pizzę z bieżącego zamówienia");
                System.out.println("5 - Dodaj promocję");
                System.out.println("6 - Rachunek");
                System.out.println("7 - Sprawdź status swojego zamówienia.");
                System.out.println("8 - Anuluj zamówienie.");
                System.out.println("9 - Wyświetl kolejkę zamówień.");
                System.out.println("10 - Wyjdź z pizzerii.");
                System.out.println("Wybierz opcję:");
                System.out.print("");
                start = Integer.parseInt(scanner.nextLine());

                switch (start) {
                    case 1:
                        PizzaFactory.displayMenu();
                        break;
                    case 2:
                       if (currentOrder != null && !currentOrder.getStatus().equals(OrderStatus.CANCELLED)) {
                            System.out.println("Już masz aktywne zamówienie (nr " + currentOrder.getIdOrder() + "). " +
                                    "Zatwierdź zamówienie (opcja 6) albo anuluj je (opcja 8), zanim złożysz nowe.");
                            break;
                       }
                        System.out.println("Podaj dane do zamówienia.");
                        int idClient = clientCounter++;
                        try {
                            System.out.print("Podaj imię klienta: ");
                            String name = scanner.nextLine();
                            System.out.print("Podaj nazwisko klienta: ");
                            String surname = scanner.nextLine();
                            System.out.print("Podaj adres klienta: ");
                            String address = scanner.nextLine();
                            System.out.print("Podaj numer telefonu klienta (format: +xx xxx xxx xxx): ");
                            String phone = scanner.nextLine();

                            currentClient = new Client(idClient, name, surname, address, phone);
                            currentOrder = new Order(currentClient);

                            orderManager.addOrder(currentOrder);
                            System.out.println("Aby kontynuować zamówienie dodaj pizzę (kliknij 3).");
                            System.out.println("Numer zamówienia: " + currentOrder.getIdOrder());
                        } catch (Exception error) {
                            System.out.println("Błąd w danych klienta: " + error.getMessage());
                        }
                        break;

                    case 3:
                        if (currentOrder == null) {
                            System.out.println("Złóż najpierw zamówienie (opcja 2).");
                            break;
                        }
                        try {
                            System.out.println("\n--- Dodawanie pizzy do zamówienia (ID = " + currentOrder.getIdOrder() + ") --- \n");
                            System.out.print("Wybierz pizzę po nazwie z menu lub wpisz custom, aby stworzyć własną kompozycję. \n");
                            String pizzaName = scanner.nextLine();
                            System.out.print("Wybierz rozmiar (SMALL, MEDIUM, LARGE): ");
                            String sizeStr = scanner.nextLine().toUpperCase();


                            Size chosenSize;
                            try {
                                chosenSize = Size.valueOf(sizeStr);
                            } catch (IllegalArgumentException error) {
                                System.out.println("Niepoprawny rozmiar. Powinno być SMALL, MEDIUM lub LARGE.\n");
                                break;
                            }

                            Pizza pizzaToAdd = null;
                            if (pizzaName.equalsIgnoreCase("custom")) {
                                CustomPizza customPizza = new CustomPizza(currentOrder.getPizzaList().size() + 1, chosenSize);

                                boolean customize = true;

                                while (customize) {
                                    try {
                                        System.out.println("\n== TWORZENIE WŁASNEJ PIZZY ==");
                                        System.out.println("1 - Dodaj składnik");
                                        System.out.println("2 - Usuń składnik");
                                        System.out.println("3 - Pokaż aktualne składniki");
                                        System.out.println("4 - Zatwierdź pizzę");
                                        System.out.print("Twój wybór: ");
                                        String option = scanner.nextLine().trim();

                                        switch (option) {
                                            case "1":
                                                PizzaFactory.displayAvailableIngredients();
                                                System.out.print("Podaj nazwę składnika do dodania: ");
                                                String toAdd = scanner.nextLine().trim();
                                                if (customPizza.addIngredient(toAdd)) {
                                                    System.out.println("Składnik dodany pomyślnie.");
                                                } else {
                                                    System.out.println("Nie udało się dodać składnika.");
                                                }
                                                break;

                                            case "2":
                                                System.out.print("Podaj nazwę składnika do usunięcia: ");
                                                String toRemove = scanner.nextLine().trim();
                                                if (customPizza.removeIngredient(toRemove)) {
                                                    System.out.println("Składnik usunięty.");
                                                } else {
                                                    System.out.println("Nie znaleziono składnika.");
                                                }
                                                break;

                                            case "3":
                                                System.out.println("Aktualne składniki:");
                                                for (Ingredient ing : customPizza.getIngredientList()) {
                                                    System.out.println("- " + ing.getName() + " (" + ing.getType() + ")");
                                                }
                                                break;

                                            case "4":
                                                try {
                                                    if (!customPizza.isValidCustomPizza()) {
                                                        System.out.println("Pizza musi zawierać przynajmniej jeden sos i jeden inny składnik.");
                                                        break;
                                                    }

                                                    Pizza verifiedCustomPizza = PizzaFactory.createCustomPizza(chosenSize, customPizza.getIngredientList());
                                                    currentOrder.addPizza(verifiedCustomPizza);
                                                    customize = false;
                                                    System.out.println("Dodano pizzę własnej kompozycji do zamówienia!");
                                                } catch (Exception error) {
                                                    System.out.println("Nie udało się zatwierdzić pizzy: " + error.getMessage());
                                                }
                                                break;

                                            default:
                                                System.out.println("Nieznana opcja.");
                                        }
                                    } catch (Exception error) {
                                        System.out.println("Wystąpił błąd podczas tworzenia własnej pizzy: " + error.getMessage());
                                    }
                                }
                            } else {
                                switch (pizzaName.toLowerCase().trim()) {
                                    case "margherita":
                                        pizzaToAdd = PizzaFactory.createMargherita(chosenSize);
                                        break;
                                    case "pepperoni":
                                        pizzaToAdd = PizzaFactory.createPepperoni(chosenSize);
                                        break;
                                    case "vegetariana":
                                        pizzaToAdd = PizzaFactory.createVegetariana(chosenSize);
                                        break;
                                    case "serowa":
                                        pizzaToAdd = PizzaFactory.createSerowa(chosenSize);
                                        break;
                                    case "farmerska":
                                        pizzaToAdd = PizzaFactory.createFarmerska(chosenSize);
                                        break;
                                    case "serowy kurczak":
                                        pizzaToAdd = PizzaFactory.createSerowyKurczak(chosenSize);
                                        break;
                                    default:
                                        System.out.println("Nie ma w menu takiej pizzy: \"" + pizzaName + "\".\n");
                                        break;
                                }
                                if (pizzaToAdd == null) {
                                    break;
                                }

                                currentOrder.addPizza(pizzaToAdd);
                                System.out.println("Dodano pizzę \"" + pizzaName + "\", rozmiar: "
                                        + chosenSize + " do zamówienia.\n");
                                break;
                            }
                        } catch (Exception error) {
                            System.out.println("Błąd: " + error.getMessage());
                        }
                        break;

                    case 4:
                        if (currentOrder == null) {
                            System.out.println("Najpierw złóż zamówienie (opcja 2).");
                            break;
                        }

                        List<Pizza> pizzas = currentOrder.getPizzaList();
                        if (pizzas.isEmpty()) {
                            System.out.println("Brak pizz w zamówieniu do usunięcia.");
                            break;
                        }
                        if(currentOrder.getStatus().equals(OrderStatus.ORDERING)) {
                            System.out.println("Aktualne pizze w zamówieniu:");
                            for (int i = 0; i < pizzas.size(); i++) {
                                System.out.println((i + 1) + ". " + pizzas.get(i));
                            }

                            System.out.print("Podaj numer pizzy do usunięcia: ");
                            try {
                                int indexToRemove = Integer.parseInt(scanner.nextLine());

                                if (indexToRemove < 1 || indexToRemove > pizzas.size()) {
                                    System.out.println("Niepoprawny numer pizzy.");
                                } else {
                                    Pizza removed = pizzas.get(indexToRemove - 1);
                                    boolean removedSuccessfully = currentOrder.removePizza(removed);
                                    if (removedSuccessfully) {
                                        System.out.println("Usunięto pizzę: " + removed);
                                    } else {
                                        System.out.println("Nie udało się usunąć pizzy.");
                                    }
                                }
                            } catch (NumberFormatException error) {
                                System.out.println("Podaj poprawny numer.");
                            }
                        }
                        break;

                    case 5:
                        if (currentOrder == null) {
                            System.out.println("Złóż najpierw zamówienie (opcja 2).");
                            break;
                        }
                        System.out.println("Dostępne promocje:");
                        System.out.println("1 - " + PromotionType.NONE.getDescription());
                        System.out.println("2 - " + PromotionType.PERCENTAGE.getDescription());
                        System.out.println("3 - " + PromotionType.BUY_TWO_GET_ONE.getDescription());
                        System.out.println("4 - " + PromotionType.FREE_DELIVERY.getDescription());
                        System.out.print("Wybierz numer promocji: ");
                        String promotionChoice = scanner.nextLine().trim();

                        PromotionType promotion = PromotionType.NONE;
                        switch (promotionChoice) {
                            case "1":
                                promotion = PromotionType.NONE;
                                break;
                            case "2":
                                promotion = PromotionType.PERCENTAGE;
                                break;
                            case "3":
                                if (currentOrder.getPizzaList().size() < 3) {
                                    System.out.println("Aby skorzystać z promocji 'Kup 2, trzecia gratis', musisz zamówić przynajmniej 3 pizze.");
                                    break;
                                }
                                promotion = PromotionType.BUY_TWO_GET_ONE;
                                break;
                            case "4":
                                promotion = PromotionType.FREE_DELIVERY;
                                break;
                            default:
                                System.out.println("Promocja nie istnieje.\n");
                                break;
                        }
                        currentOrder.setPromotion(promotion);
                        System.out.println("Dodano promocję do zamówienia: " + promotion.getDescription() + "\n");
                        break;

                    case 6:
                        if (currentOrder == null) {
                            System.out.println("Najpierw złóż zamówienie (opcja 2).");
                            break;
                        } else if (currentOrder.getPizzaList().isEmpty()) {
                            System.out.println("Dodaj przynajmniej jedną pizzę do zamówienia (opcja 3).");
                            break;
                        }

                        System.out.println("Zamówienie nr " + currentOrder.getIdOrder() + "\n");
                        System.out.println("Zamówione pizze:");
                        System.out.println(currentOrder.getPizzaList());
                        System.out.println("Promocja - " + currentOrder.getPromotion() + "\n");
                        System.out.println("Cena do zapłaty: " + currentOrder.calculateTotalPrice() + "zł.");

                        currentOrder.updateOrderStatus(OrderStatus.ORDER_PLACED);
                        currentOrder.scheduleStatusUpdates();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        LocalDateTime readyTime = currentOrder.calculateReadyTime(LocalDateTime.now());
                        System.out.println("Zamówienie powinno być gotowe około: " + readyTime.format(formatter));

                        currentOrder = null;
                        currentClient = null;

                        System.out.println("Zamówienie zostało zatwierdzone.");

                        break;

                    case 7:
                        System.out.print("Podaj numer zamówienia, którego status chcesz sprawdzić: ");
                        try {
                            int idToCheck = Integer.parseInt(scanner.nextLine().trim());
                            Order orderToCheck = orderManager.getOrderById(idToCheck);
                            System.out.println("Status zamówienia nr " + idToCheck + ": " +
                                    orderToCheck.getStatus().getDescription());
                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowy format numeru. Podaj liczbę całkowitą.");
                        } catch (NoSuchElementException e) {
                            System.out.println("Nie znaleziono zamówienia o tym numerze.");
                        }
                        break;


                    case 8:
                        try {
                            System.out.println("Podaj numer zamówienia, aby je anulować.");
                            int idOrderCancel = Integer.parseInt(scanner.nextLine().trim());
                            Order orderToCancel = orderManager.getOrderById(idOrderCancel);

                            if (orderToCancel.getStatus().equals(OrderStatus.READY)) {
                                System.out.println("Nie można anulować gotowego zamówienia.");
                            } else {
                                orderManager.updateOrderStatus(idOrderCancel, OrderStatus.CANCELLED);
                                System.out.println("Zamówienie zostało anulowane.");

                                if (orderToCancel.equals(currentOrder)) {
                                    currentOrder = null;
                                    currentClient = null;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowy numer zamówienia. Podaj liczbę całkowitą.");
                        } catch (NoSuchElementException e) {
                            System.out.println("Nie znaleziono zamówienia o tym numerze.");
                        }
                        break;

                    case 9:
                        System.out.println("Kolejka wszystkich zamówień: \n" + orderManager.getOrderQueue());
                        break;

                    case 10:
                        System.out.println("Do zobaczenia :)\n");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.\n");
                        System.exit(0);
                        break;
                }

            } catch (Exception error) {
                System.out.println("Błąd:" + error.getMessage());
            }
        }
    }
}
