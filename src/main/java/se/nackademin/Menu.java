package se.nackademin;

import java.io.IOException;
import java.util.Scanner;

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
        switch (choice) {
            case 1:
                System.out.println("Connecting to database");
                JdbcUtilsForMenu.connectToDatabase();
                JdbcUtilsForMenu.createDatabase("Cafeteria");
                JdbcUtilsForMenu.createTableMenu();
                JdbcUtilsForMenu.createTableOrders();
                JdbcUtilsForMenu.insertDrinksIntoTable();
                break;
            case 2:
                System.out.println("View beverage menu");
                JdbcUtilsForMenu.connectToDatabase();
                List<CoffeeDrink>beverageMenu = JdbcUtilsForMenu.listBeverageMenuDetails();
                beverageMenu.forEach(System.out::println);
                break;
            case 3:
                //System.out.println("Place order");
                //    Scanner sc = new Scanner(System.in);
                //    System.out.print("Enter title of beverage: ");
                //    String title = sc.nextLine();
                //    System.out.print("Enter a drink size: ");
                //    String size = sc.nextLine();
                //    System.out.print("Additives Y or N?");
                //    String answer = sc.nextLine();
                //    if (answer.equals("Y")){
                //        System.out.print("Enter additives: ");
                //        String additive = sc.nextLine();
                //    }
                //        else {
                //        break;
                //        }
                //    System.out.print("Sweetener Y or N?");
                //    String answer2 = sc.nextLine();
                //    if (answer2.equals("Y")){
                //        System.out.print("Enter sweeteners: ");
                //        String sweetener = sc.nextLine();
                //    }
                //    else {
                //        break;
                //    }
                //break;
                System.out.println("Place order");
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter title of beverage: ");
                String coffee = sc.nextLine();
                System.out.println("Enter size: ");
                DrinkSizes size = DrinkSizes.valueOf(sc.nextLine());
                JdbcUtilsForMenu.connectToDatabase();
                CoffeeDrink result = null;
                switch (coffee) {
                    case "Coffee":
                        result = JdbcUtilsForMenu.getRow(coffee, size);
                        //String getName;
                        //int coffeeId = 1;
                        //getName = listDrinkDetails.get(coffeeId);
                        System.out.print("Enter additives: ");
                        DrinkAdditives additive = DrinkAdditives.valueOf(sc.nextLine());
                        System.out.println("Enter sweeteners: ");
                        DrinkSweeteners sweetener = DrinkSweeteners.valueOf(sc.nextLine());
                        result.setDrinkAdditive(additive);
                        result.setDrinkSweetener(sweetener);
                        JdbcUtilsForMenu.insertOrder(result);
                    case "Cappuccino":
                        result = JdbcUtilsForMenu.getRow(coffee, size);
                    case "Latte":
                        result = JdbcUtilsForMenu.getRow(coffee, size);
                    case "Americano":
                        result = JdbcUtilsForMenu.getRow(coffee, size);
                    case "Espresso":
                        result = JdbcUtilsForMenu.getRow(coffee, size);
                    case "Macchiato":
                        result = JdbcUtilsForMenu.getRow(coffee, size);
                    case "Ice Coffee":
                        result = JdbcUtilsForMenu.getRow(coffee, size);
                    }
                System.out.println(result);
            case 4:
                System.out.println("View orders");
                break;
            default:
                System.out.println("Unknown selection");
        }
    }
}

