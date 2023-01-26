package se.nackademin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import se.nackademin.Beverages.DrinkAdditives;
import se.nackademin.Beverages.DrinkSizes;
import se.nackademin.Beverages.DrinkSweeteners;
import se.nackademin.CoffeeDrink;

public class JDBCUtils {
    String hostname;
    String userName;
    String password; // Never add hardcoded passwords to your code
    String port;
    private static Connection conn;

    JDBCUtils(String hostname, String port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //return list of Menu
    public List<CoffeeDrink> listBeverageMenuDetails() {
        List<CoffeeDrink> list = new ArrayList<CoffeeDrink>();
        CoffeeDrink coffeeDrink = null;
        ResultSet rs = null;
        Statement stmt = null;
        // Write the SQL query
        String query = "select * from Beverage_menu";
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(query);
            // Iterate the whole resultset and add the data
            // in the list
            while (rs.next()) {
                int drinkId = rs.getInt("Id");
                String drinkTitle = rs.getString("title");
                DrinkSizes drinkSize =  DrinkSizes.valueOf(rs.getString("size"));
                double drinkPrice = rs.getDouble("price");

                coffeeDrink = new CoffeeDrink(drinkTitle, drinkSize, drinkPrice);
                coffeeDrink.setDrinkId(drinkId);
                list.add(coffeeDrink);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // create db method
    public void createDatabase(String name) {
        String createString = "create database IF NOT EXISTS " + name;
        try (Statement stmt = this.conn.createStatement()) {
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //connect to db method
    public void connectToDatabase() {
        try {
            this.setUsername("root"); // Never use the root user in real apps
            this.setPassword("miradocker94"); // Never add hardcoded passwords to your code
            this.conn = this.getConnection();
            }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Something went wrong with mysql");
            }

    }

    // create table Menu
    public void createTableMenu() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Beverage_menu (
            id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            title TEXT,
            size TEXT,
            price DECIMAL(5,2)
            )
            """;
            try (Statement stmt = this.conn.createStatement()) {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    //create table orders
    public void createTableOrders() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Orders (
                id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                title TEXT,
                size TEXT,
                price DECIMAL(5,2),
                additive TEXT,
                sweetener TEXT
                )
                """;
            try (Statement stmt = this.conn.createStatement()) {
                    stmt.executeUpdate(sql);
            } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    //insert hardcoded data into table menu
    public void insertDrinksIntoTable() {
        String insertCoffee = "INSERT INTO Beverage_menu(title, size, price)" +
                                        "VALUES('Coffee', 'SMALL', 35), ('Coffee', 'MEDIUM', 45), ('Coffee', 'LARGE', 55)";
        String insertCappuccino = "INSERT INTO Beverage_menu(title, size, price)" +
                                        "VALUES('Cappuccino', 'SMALL', 55), ('Cappuccino', 'MEDIUM', 65), ('Cappuccino', 'LARGE', 75)";
        String insertLatte = "INSERT INTO Beverage_menu(title, size, price)" +
                                        "VALUES('Latte', 'SMALL', 55), ('Latte', 'MEDIUM', 65), ('Latte', 'LARGE', 75)";
        String insertAmericano = "INSERT INTO Beverage_menu(title, size, price)" +
                                        "VALUES('Americano', 'SMALL', 45), ('Latte', 'MEDIUM', 55), ('Latte', 'LARGE', 65)";
        String insertEspresso = "INSERT INTO Beverage_menu(title, size, price)" +
                                        "VALUES('Espresso', 'SMALL', 45), ('Espresso', 'MEDIUM', 55), ('Espresso', 'LARGE', 65)";
        String insertMacchiato = "INSERT INTO Beverage_menu(title, size, price)" +
                                        "VALUES('Macchiato', 'SMALL', 55), ('Macchiato', 'MEDIUM', 65), ('Macchiato', 'LARGE', 75)";
        String insertIceCoffee = "INSERT INTO Beverage_menu(title, size, price)" +
                                        "VALUES('Ice Coffee', 'SMALL', 35), ('Ice Coffee', 'MEDIUM', 45), ('Ice Coffee', 'LARGE', 55)";
        try (Statement stmt = this.conn.createStatement()) {
                stmt.executeUpdate(insertCoffee);
                stmt.executeUpdate(insertCappuccino);
                stmt.executeUpdate(insertLatte);
                stmt.executeUpdate(insertAmericano);
                stmt.executeUpdate(insertEspresso);
                stmt.executeUpdate(insertMacchiato);
                stmt.executeUpdate(insertIceCoffee);
        } catch (SQLException e) {
                System.out.println(e);
            }
        }

    //insert data into Orders
    public List<CoffeeDrink> listOrders() {
        List<CoffeeDrink> list = new ArrayList<CoffeeDrink>();
        CoffeeDrink coffeeDrink = null;
        ResultSet rs = null;
        Statement stmt = null;
        // Write the SQL query
        String query = "select * from Orders";
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(query);
            // Iterate the whole resultset and add the data
            // in the list
            while (rs.next()) {
                int drinkId = rs.getInt("Id");
                String drinkTitle = rs.getString("title");
                DrinkSizes drinkSize =  DrinkSizes.valueOf(rs.getString("size"));
                double drinkPrice = rs.getDouble("price");
                DrinkAdditives drinkAdditive = DrinkAdditives.valueOf(rs.getString("additive"));
                DrinkSweeteners drinkSweetener = DrinkSweeteners.valueOf(rs.getString("sweetener"));

                coffeeDrink = new CoffeeDrink(drinkTitle, drinkSize, drinkPrice);
                coffeeDrink.setDrinkId(drinkId);
                coffeeDrink.setDrinkAdditive(drinkAdditive);
                coffeeDrink.setDrinkSweetener(drinkSweetener);
                list.add(coffeeDrink);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // getting row for mapping coffeeDrink from menu for make order
    public CoffeeDrink getRow(String title, DrinkSizes size) {
        String order = "SELECT * FROM Beverage_menu WHERE title = ? AND size = ? ";
        PreparedStatement myStmt;
        CoffeeDrink coffeeDrink = null;
        try (Statement stmt = this.conn.createStatement()) {
                myStmt = this.conn.prepareStatement(order);
                myStmt.setString(1, title);
                myStmt.setString(2, size.toString());
                ResultSet rs = myStmt.executeQuery();
            while (rs.next()) {
                    String drinkTitle = rs.getString("title");
                    DrinkSizes drinkSize =  DrinkSizes.valueOf(rs.getString("size"));
                    double drinkPrice = rs.getDouble("price");
                    coffeeDrink = new CoffeeDrink(drinkTitle, drinkSize, drinkPrice);
                }
        } catch (SQLException e) {
                System.out.println(e);
            }
        return coffeeDrink;
        }

    // another method for insert into table orders
    public String insertOrder(CoffeeDrink coffee) {
        String insertOrder = "INSERT INTO Orders (title, size, price, additive, sweetener)" +
                            "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement myStmt;
        String result = null;
        try (Statement stmt = this.conn.createStatement()) {
            myStmt = this.conn.prepareStatement(insertOrder);
            myStmt.setString(1, coffee.getDrinkTitle());
            myStmt.setString(2, coffee.getDrinkSize().toString());
            myStmt.setDouble(3, coffee.getDrinkPrice());
            myStmt.setString(4, coffee.getDrinkAdditives().toString());
            myStmt.setString(5, coffee.getDrinkSweeteners().toString());
            result = String.valueOf(myStmt.executeUpdate());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
        }

    //Method that returns connection for db
    public Connection getConnection() throws SQLException {
        //Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        // Modify the "/" after this.port to set a specific database
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/Cafeteria", connectionProps);
        return conn;
    }
}

