package se.nackademin;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import se.nackademin.Beverages.DrinkSizes;
/**
* The JDBCUtilsTest class is used to test the JDBCUtils class, which contaiins methods to intetact with a MySQL database.
*/
public class JDBCUtilsTest {
    /**
     * Creates a JDBCUtils object with the given IP address and port number to connect to the MySQL database.
     * Tests the default menu of CoffeeDrink objects by connecting to the database and calling the listBeverageMenuDetails() method from JDBUtils class.
     */
    JDBCUtils JDBCUtilsObject = new JDBCUtils("127.0.0.1", "3306");
    @Test
    public void checkDefaultMenu() {
        JDBCUtilsObject.connectToDatabase();
        List<CoffeeDrink> listDrinkDetails = JDBCUtilsObject.listBeverageMenuDetails();
        // Checking whether totally 21 drinks are available
        assertEquals(listDrinkDetails.size(), 21);

        // Checking whether first drink id is 1
        assertEquals(listDrinkDetails.get(0).getDrinkId(),1);

        // Checking whether first drink title is Coffee
        assertEquals(listDrinkDetails.get(0).getDrinkTitle(),"Coffee");

        // Checking whether last drink title is Ice coffee
        assertEquals(listDrinkDetails.get(20).getDrinkTitle(),"Ice Coffee");

        // Checking whether second  drink size is MEDIUM
        assertEquals(listDrinkDetails.get(1).getDrinkSize(), DrinkSizes.MEDIUM);

        // Checking whether 3th drink size is LARGE
        assertEquals(listDrinkDetails.get(2).getDrinkSize(), DrinkSizes.LARGE);

        // Checking whether second  drink price is 45.00
        assertEquals(listDrinkDetails.get(1).getDrinkPrice(),45.00, 0);

    }

    /**
     * Tests the behavior of the place order method in the system.
     * Connect to the databse using the JDBUtilsObject.
     * Inserts a new order for a small coffee with the title "Coffee" and the price of 35.00.
     * Retrieves the list of orders using the JDBUtilsObject.
     * Checks that the price of the last (i.e., most recent) order in the list is 35.00.
     * Checks that the title of the last order in the list is "Coffee".
     * Checks that the size of the last order is small.
     */
    @Test
    public void checkPlaceOrder() {
        JDBCUtilsObject.connectToDatabase();
        JDBCUtilsObject.insertOrder(new CoffeeDrink("Coffee", DrinkSizes.SMALL, 35.00));
        List<CoffeeDrink>orders = JDBCUtilsObject.listOrders();

        assertEquals(orders.get(orders.size() -1).getDrinkPrice(), 35.00, 0);

        assertEquals(orders.get(orders.size() -1).getDrinkTitle(), "Coffee");

        assertEquals(orders.get(orders.size() -1).getDrinkSize(), DrinkSizes.SMALL);
}
}
