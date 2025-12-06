# Console Pizza Ordering System
This application was created during the 2nd semester of Computer Science studies as a final project for the Object-Oriented Programming (OOP) course.
It is a collaborative group project designed to practice Java and OOP principles. The application simulates a pizzeria. The system allows customers to create orders and track their status in the console.

## My Contribution
This project was a collaborative effort. While the base architecture and some initial methods were established by my teammate, my specific responsibilities included:

* **System Core & Console Interface (`PizzeriaSystem`):**
  * Within the shared class structure, I implemented the **logic for the console menu**.
  * It handles user input via the console, enabling the user to navigate the menu and trigger specific system actions (like creating an order or checking status) without errors.

* **Validation & Security:**
  * Implemented strict data validation in `Client` class using Regular Expressions to check if the name contains only letters and if the phone number has the correct format.
  * Added checks in `Ingredient` to prevent critical errors like negative prices or inventory counts.
  * Implemented logic in `PizzaFactory` to verify ingredient availability in the "fridge" before processing an order.
  * Protected `OrderManager` against invalid operations, such as modifying cancelled orders.
  * I implemented error handling throwing `IllegalArgumentException` with custom messages for invalid inputs.

* **Menu Implementation:**
  * I wrote the code for specific pizzas (e.g., `Farmerska`, `Serowa`) by defining their ingredient lists in the constructors.
  * I implemented the `displayMenu` method to print available pizzas to the console.
  * I implemented rules for **Custom Pizza**, ensuring that a user cannot place an invalid order (e.g., a pizza without sauce).
  * I added a simple **Inventory Management** mechanism that automatically decreases the stock of ingredients in the "fridge" after every order.

## Functionality
* **Create New Order:**
  * Collecting client data (Name, Surname, Contact info).
  * Initializing an empty cart associated with the client.
* **Add Pizza to Order:**
  * Selection from pre-defined menu (each pizza is a separate class inheriting from the abstract `Pizza` class, e.g., `Margherita`, `Pepperoni`, `Farmerska`, `Vegetariana`, `SerowyKurczak`, `Serowa`).
  * **Custom Pizza Builder:** Allows users to compose their own pizza (selecting size and ingredients).
* **Remove Pizza from Order**
* **Promotion Selection:**
  * No promotion
  * Percentage discount
  * "Buy 2, Get 1 Free"
  * Free delivery
* **Price Calculation:** Dynamic calculation based on ingredients, pizza size, and applied promotion.
* **Order Status Management:**
  * `ORDERING` – In progress
  * `ORDER_PLACED` – Placed
  * `IN_PREPARATION` – In preparation
  * `READY` – Ready for pickup
  * `CANCELLED` – Cancelled
* **Automatic Status Updates:** Simulation of preparation stages over time.
* **Order Cancellation:** Ability to cancel the order before preparation starts.
* **Order Tracking:** Check order status by ID.
* **Queue View:** Display all active orders.

## Project Structure

* **`PizzeriaSystem.java`** – Main application interface containing the menu loop and user interaction handling.
* **`OrderManager.java`** – Logic for order management: creation, processing, and status updates.
* **`Order.java`** – Represents a single order entity (Client data, selected pizzas, promotion).
* **`Pizza.java`** – Abstract class defining common features of all pizzas.
  * **`CustomPizza.java`** – Represents a pizza manually composed by the user.
  * **`Margherita.java`**, **`Pepperoni.java`**, etc. – Concrete pizza variants created based on `Pizza.java`.
* **`PizzaFactory.java`** – Factory responsible for creating pizza instances (both pre-defined and custom) and managing ingredient inventory.
* **`Ingredient.java`** – Represents a single pizza ingredient, mainly used in `CustomPizza`.
* **`TypeOfIngredients.java`** – Enum defining ingredient types (e.g., Cheese, Meat, Vegetable).
* **`Client.java`** – Stores data of the client placing the order.
* **`PromotionType.java`** – Enum containing available promotion types.
* **`Size.java`** – Enum defining pizza sizes.
* **`OrderStatus.java`** – Enum describing possible order statuses.
* **`Main.java`** – Application entry point, initializes `PizzeriaSystem`.

## How to Run

1.  Download the source code or clone the repository.
2.  Open the folder containing `.java` files in your IDE (e.g., IntelliJ IDEA).
3.  Compile all files.
4.  Run the `Main.java` class.
5.  Use the numbered options displayed in the console menu.

## Requirements

* Java 8 or higher
* Console/Terminal for text input

## Additional Information

The system uses a simple time simulator to automatically update order statuses after placement. Promotions apply based on specific conditions (e.g., 3 pizzas are required for "2+1 Free"). When creating a custom pizza, at least two ingredients are required (one sauce + one other), and the maximum limit is 7 ingredients.

## Note on Language
The user interface (CLI), error messages are in **Polish**, as this project was originally developed to meet specific coursework requirements at a Polish university.