package se.nackademin;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;

import se.nackademin.Beverages.DrinkAdditives;
import se.nackademin.Beverages.DrinkSizes;
import se.nackademin.Beverages.DrinkSweeteners;

import java.sql.SQLException;
import java.sql.Connection;

import java.util.List;

public class Menu {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            menu(sc);
            }
        }


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

    static void menuChoices(int choice) throws IOException, ClassNotFoundException {
        JDBCUtils JdbcUtilsForMenu = new JDBCUtils("127.0.0.1", "3306");
        JdbcUtilsForMenu.connectToDatabase();
        switch (choice) {
            case 1:
                JdbcUtilsForMenu.createDatabase("Cafeteria");
                JdbcUtilsForMenu.createTableMenu();
                JdbcUtilsForMenu.createTableOrders();
                //JdbcUtilsForMenu.insertDrinksIntoTable();
                break;
            case 2:
                System.out.println("View beverage menu");
                List<CoffeeDrink>beverageMenu = JdbcUtilsForMenu.listBeverageMenuDetails();
                beverageMenu.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Place order");
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter title of beverage: ");
                String coffee = sc.nextLine();
                System.out.println("Enter size: ");
                DrinkSizes size = DrinkSizes.valueOf(sc.nextLine().toUpperCase());
                //JdbcUtilsForMenu.connectToDatabase();
                CoffeeDrink result = null;
                DrinkSweeteners sweetener = null;
                DrinkAdditives additive = null;
                result = JdbcUtilsForMenu.getRow(coffee, size);
                switch (coffee) {
                    case "Coffee":
                        System.out.println(java.util.Arrays.asList(DrinkAdditives.values()));
                        System.out.println("Enter additives: ");
                        additive = DrinkAdditives.valueOf(sc.nextLine().toUpperCase());

                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkAdditive(additive);
                        result.setDrinkSweetener(sweetener);
                        break;

                    case "Cappuccino":
                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkSweetener(sweetener);
                    case "Latte":

                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkSweetener(sweetener);
                        break;

                    case "Americano":
                        System.out.println(java.util.Arrays.asList(DrinkAdditives.values()));
                        System.out.println("Enter additives: ");
                        additive = DrinkAdditives.valueOf(sc.nextLine().toUpperCase());

                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkAdditive(additive);
                        result.setDrinkSweetener(sweetener);
                        break;
                    case "Espresso":
                        System.out.println(java.util.Arrays.asList(DrinkSweeteners.values()));
                        System.out.println("Enter sweeteners: ");
                        sweetener = DrinkSweeteners.valueOf(sc.nextLine().toUpperCase());

                        result.setDrinkSweetener(sweetener);
                        break;

                    case "Macchiato":
                        break;

                    case "Ice Coffee":
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
                System.out.println(result);
            case 4:
                System.out.println("View orders");
                JdbcUtilsForMenu.read("Orders");
                break;
            default:
                System.out.println("Unknown selection");
        }
    }
}

