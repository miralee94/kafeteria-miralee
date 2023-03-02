package se.nackademin;

import java.io.IOException;
import java.util.Scanner;
import se.nackademin.Beverages.DrinkAdditives;
import se.nackademin.Beverages.DrinkSizes;
import se.nackademin.Beverages.DrinkSweeteners;
import java.util.List;


public class Menu {
    /**
    * The main method of the application, which sets up a Scanner object to read user input and calls the menu() method to display the menu of available options.
    * @param args an array of command-line arguments
    * @throws Exception
    */
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            menu(sc);
            }
        }
    /**
     * Displays the menu of available options to the user and reads their input to determine which action to take.
     * @param sc a Scanner object used to read user input
     * @throws IOException if an I/O error occurs while reading input
     * @throws ClassNotFoundException if the JDBC driver class cannot be found
     */
    static void menu(Scanner sc) throws IOException, ClassNotFoundException {
        while (true) {
            String menuText = """
                    Enter 1-4
                    1. Connect to mysql
                    2. View beverage menu
                    3. Place order
                    4. View orders
                    or 'exit' to quit:
                    """;

            System.out.println(menuText);
            if (sc.hasNextInt()) {
                int choice = Integer.valueOf(sc.nextLine());
                Menu.menuChoices(choice);
            } else {
                if (sc.nextLine().equals("exit")) {
                    break;
                }
            }
        }
    }
    /**
    * Processes the user's choice from the menu and performs the appropriate action.
    * @param choice the user's choice from the menu
    */
    static void menuChoices(int choice) throws IOException, ClassNotFoundException {
        JDBCUtils JdbcUtilsForMenu = new JDBCUtils("127.0.0.1", "3306");
        JdbcUtilsForMenu.connectToDatabase();
        switch (choice) {
            case 1:
                JdbcUtilsForMenu.createDatabase("Cafeteria");
                JdbcUtilsForMenu.createTableMenu();
                JdbcUtilsForMenu.createTableOrders();
                JdbcUtilsForMenu.insertDrinksIntoTable();
                break;
            case 2:
                System.out.println("View beverage menu");
                List<CoffeeDrink>beverageMenu = JdbcUtilsForMenu.listBeverageMenuDetails();
                beverageMenu.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Place order");
                System.out.println("Enter a title of beverage: ");
                Scanner sc = new Scanner(System.in);
                String coffee = sc.nextLine().toUpperCase();
                System.out.println("Enter size: ");
                DrinkSizes size = DrinkSizes.valueOf(sc.nextLine().toUpperCase());
                CoffeeDrink result = null;
                DrinkSweeteners sweetener = null;
                DrinkAdditives additive = null;
                result = JdbcUtilsForMenu.getRow(coffee, size);
                switch (coffee) {
                    case "COFFEE":
                        System.out.println(java.util.Arrays.asList(DrinkAdditives.values()));
                        System.out.println("Enter additives: ");
                        additive = DrinkAdditives.valueOf(sc.nextLine().toUpperCase());

                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkAdditive(additive);
                        result.setDrinkSweetener(sweetener);
                        break;

                    case "CAPPUCCINO":
                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkSweetener(sweetener);
                    case "LATTE":

                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkSweetener(sweetener);
                        break;

                    case "AMERICANO":
                        System.out.println(java.util.Arrays.asList(DrinkAdditives.values()));
                        System.out.println("Enter additives: ");
                        additive = DrinkAdditives.valueOf(sc.nextLine().toUpperCase());

                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkAdditive(additive);
                        result.setDrinkSweetener(sweetener);
                        break;
                    case "ESPRESSO":
                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkSweetener(sweetener);
                        break;

                    case "MACCHIATO":
                        break;

                    case "ICE COFFEE":
                        System.out.println(java.util.Arrays.asList(DrinkAdditives.values()));
                        System.out.println("Enter additives: ");
                        additive = DrinkAdditives.valueOf(sc.nextLine().toUpperCase());

                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkAdditive(additive);
                        result.setDrinkSweetener(sweetener);
                        break;
                    }
                JdbcUtilsForMenu.insertOrder(result);
                System.out.println("Done!");
                break;
            case 4:
                System.out.println("View orders");
                List<CoffeeDrink>orders = JdbcUtilsForMenu.listOrders();
                orders.forEach(System.out::println);
                break;
            default:
                System.out.println("Unknown selection");
        }
    }
}

