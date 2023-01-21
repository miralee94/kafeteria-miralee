package se.nackademin;

import java.io.IOException;
import java.util.Scanner;

import se.nackademin.JBCUtils.JDBCUtils;

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
            System.out.println("Enter 1-4");
            System.out.println("1. Connect to mysql");
            System.out.println("2. View beverage menu");
            System.out.println("2. Place order");
            System.out.println("3. View orders");
            System.out.println("or 'exit' to quit: ");
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

    static void menu_choices(int i) throws IOException, ClassNotFoundException {
        switch (i) {
            case 1:
            try {
                System.out.println("Connect to mysql");
                JDBCUtils jdbcUtils = new JDBCUtils("127.0.0.1", "3306");
                jdbcUtils.setUsername("root"); // Never use the root user in real apps
                jdbcUtils.setPassword("miradocker94"); // Never add hardcoded passwords to your code

                Connection conn = jdbcUtils.getConnection();
                jdbcUtils.createDatabase(conn, "Beverage menu"); // This will create the database with no tables
                jdbcUtils.createTable(conn);
                jdbcUtils.insertIntoTable(conn);
                jdbcUtils.readTable(conn, "drinks");
                jdbcUtils.updateValue(conn);
                jdbcUtils.deleteRow(conn);
                jdbcUtils.readTable(conn, "drinks");
                }
            catch (SQLException e){
                System.out.println("Something went wrong with mysql");
                }
            finally {
                System.out.println("I am in final block");
                }
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
