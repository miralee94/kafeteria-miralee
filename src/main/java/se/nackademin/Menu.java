package se.nackademin;

import java.io.IOException;
import java.util.Scanner;
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
                    2. Place order
                    3. View orders
                    or 'exit' to quit:
                    """;

            System.out.println(menuText);
            if (sc.hasNextInt()) {
                int choice = Integer.valueOf(sc.nextLine());
                Menu.menu_choices(choice);
            } else {
                if (sc.nextLine().equals("exit")) {
                    break;
                }
            }
        }
    }

    static void menu_choices(int choice) throws IOException, ClassNotFoundException {
        JDBCUtils JdbcUtilsForMenu = new JDBCUtils("127.0.0.1", "3306");
        switch (choice) {
            case 1:
                System.out.println("Connecting to database");
                JdbcUtilsForMenu.connectToDatabase();
                JdbcUtilsForMenu.createDatabase("Cafeteria");
                JdbcUtilsForMenu.createTable();
                JdbcUtilsForMenu.insertDrinksIntoTable();
                break;
            case 2:
                System.out.println("View beverage menu");
                JdbcUtilsForMenu.connectToDatabase();
                List<CoffeeDrink>beverageMenu = JdbcUtilsForMenu.listBeverage_menuDetails();
                beverageMenu.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Place order");
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter title of beverage: ");
                    String title = sc.nextLine();
                    System.out.print("Enter a drink size: ");
                    String size = sc.nextLine();
                    System.out.print("Additives Y or N?");
                        System.out.print("Enter additives: ");
                        String additive = sc.nextLine();
                    System.out.print("Sweetener Y or N?");
                        System.out.print("Enter sweeteners: ");
                        String sweetener = sc.nextLine();
                break;
            case 4:
                System.out.println("View orders");
                break;
            default:
                System.out.println("Unknown selection");
        }
    }
}

