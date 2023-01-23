package se.nackademin;

import java.io.IOException;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;

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
        switch (choice) {
            case 1:
                System.out.println("Connecting to database");
                break;
            case 2:
                System.out.println("View beverage menu");
                break;
            case 3:
                System.out.println("Place order");
                break;
            case 4:
                System.out.println("View orders");
                break;
            default:
                System.out.println("Unknown selection");
        }
    }
}

