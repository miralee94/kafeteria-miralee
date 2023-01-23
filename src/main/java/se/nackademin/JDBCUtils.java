package se.nackademin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.util.ArrayList;
import java.util.List;
import se.nackademin.Beverages.DrinkSizes;
import se.nackademin.CoffeeDrink;
public class JDBCUtils {
    String hostname;
    String userName;
    String password; // Never add hardcoded passwords to your code
    String port;
    Connection conn;

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

    public List<CoffeeDrink> listCoffeeDrinksDetails() {
        List<CoffeeDrink> list = new ArrayList<CoffeeDrink>();
        CoffeeDrink coffeeDrink = null;
        ResultSet rs = null;
        Statement stmt = null;
        // Write the SQL query
        String query = "select * from coffeedrinks";
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(query);
            // Iterate the whole resultset and add the data
            // in the list
            while (rs.next()) {
                String drinkTitle = rs.getString("title");
                DrinkSizes drinkSize =  DrinkSizes.valueOf(rs.getString("size"));
                double drinkPrice = rs.getDouble("price");
                

                coffeeDrink = new CoffeeDrink(drinkTitle, drinkSize, drinkPrice);

                list.add(coffeeDrink);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void createDatabase(Connection conn, String name) throws SQLException {
        String createString = "create database IF NOT EXISTS mydb";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectToDatabase() {
        try {
            System.out.println("Connect to mysql");
            JDBCUtils jdbcUtils = new JDBCUtils("127.0.0.1", "3306");
            jdbcUtils.setUsername("root"); // Never use the root user in real apps
            jdbcUtils.setPassword("miradocker94"); // Never add hardcoded passwords to your code

            this.conn = jdbcUtils.getConnection();
            //jdbcUtils.createDatabase(conn, "Beverage menu"); // This will create the database with no tables
            //jdbcUtils.createTable(conn);
            //jdbcUtils.insertIntoTable(conn);
            //jdbcUtils.readTable(conn, "drinks");
            //jdbcUtils.updateValue(conn);
            //jdbcUtils.deleteRow(conn);
            //jdbcUtils.readTable(conn, "drinks");
            }
        catch (SQLException e){
            System.out.println("Something went wrong with mysql");
            }
        finally {
            System.out.println("I am in final block");
            }
    }

    public void createTable(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS Beverage menu (
            id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            title TEXT,
            size TEXT,
            price DECIMAL(5,2)
            )
            """;
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void insertIntoTable(Connection conn) throws SQLException {
            String insertCoffee = "INSERT INTO coffeedrinks(title, size, price)" +
                                        "VALUES('Coffe', 'SMALL' 35), ('Coffee', 'MEDIUM', 45), ('Coffee', 'LARGE', 55)";
            String insertCappuccino = "INSERT INTO coffeedrinks(title,size, price)" +
                                        "VALUES('Cappuccino', 'SMALL' 55), ('Cappuccino', 'MEDIUM', 65), ('Cappuccino', 'LARGE', 75)";
            String insertLatte = "INSERT INTO coffeedrinks(title, size, price)" +
                                        "VALUES('Latte', 'SMALL' 55), ('Latte', 'MEDIUM', 65), ('Latte', 'LARGE', 75)";
            String insertAmericano = "INSERT INTO coffeedrinks(title, size, price)" +
                                        "VALUES('Americano', 'SMALL' 45), ('Latte', 'MEDIUM', 55), ('Latte', 'LARGE', 65)";
            String insertEspresso = "INSERT INTO coffeedrinks(title, size, price)" +
                                        "VALUES('Espresso', 'SMALL' 45), ('Espresso', 'MEDIUM', 55), ('Espresso', 'LARGE', 65)";
            String insertMacchiato = "INSERT INTO coffeedrinks(title, size, price)" +
                                        "VALUES('Macchiato', 'SMALL' 55), ('Macchiato', 'MEDIUM', 65), ('Macchiato', 'LARGE', 75)";
            String insertIceCoffee = "INSERT INTO coffeedrinks(title, size, price)" +
                                        "VALUES('Ice Coffe', 'SMALL' 35), ('Ice Coffee', 'MEDIUM', 45), ('Ice Coffee', 'LARGE', 55)";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(insertCappuccino);
                stmt.executeUpdate(insertCoffee);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        public void read(Connection conn, String tableName) throws SQLException {
            String select = "SELECT * FROM " + tableName;

            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(select);
                while (rs.next()) {
                    String title = rs.getString(2);
                    String price = rs.getString(3);
                    System.out.println(title + " " + price);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        public void updateValue(Connection conn) throws SQLException {
            String update = "UPDATE drinks SET price = 65 WHERE title = 'Cappuccino'";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(update);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        public void deleteRow(Connection conn) throws SQLException {
            String update = "DELETE FROM drinks WHERE price = 55";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(update);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        // Modify the "/" after this.port to set a specific database
        conn = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/mydb", connectionProps);

        System.out.println("Connected to db");
        return conn;
    }
}

