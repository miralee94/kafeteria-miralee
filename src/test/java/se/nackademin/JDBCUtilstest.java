package se.nackademin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import se.nackademin.Beverages.DrinkSizes;

import java.util.ArrayList;
import java.util.List;

//import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JDBCUtilsTest {

    @Mock
    private JDBCUtils jdbcUtils;
    List<CoffeeDrink> listDrinkDetails;

    @BeforeEach
    public void setup() {
        // create a list of 21 items
        listDrinkDetails = new ArrayList<>();
        listDrinkDetails.add(new CoffeeDrink("Coffee", Beverages.DrinkSizes.SMALL, 35.00));
        listDrinkDetails.add(new CoffeeDrink("Coffee", Beverages.DrinkSizes.MEDIUM, 45.00));
        listDrinkDetails.add(new CoffeeDrink("Coffee", Beverages.DrinkSizes.LARGE, 55.00));
        listDrinkDetails.add(new CoffeeDrink("Cappuccino", Beverages.DrinkSizes.SMALL, 55.00));
        listDrinkDetails.add(new CoffeeDrink("Cappuccino", Beverages.DrinkSizes.MEDIUM, 65.00));
        listDrinkDetails.add(new CoffeeDrink("Cappuccino", Beverages.DrinkSizes.LARGE, 75.00));
        listDrinkDetails.add(new CoffeeDrink("Latte", Beverages.DrinkSizes.SMALL, 55.00));
        listDrinkDetails.add(new CoffeeDrink("Latte", Beverages.DrinkSizes.MEDIUM, 65.00));
        listDrinkDetails.add(new CoffeeDrink("Latte", Beverages.DrinkSizes.LARGE, 75.00));
        listDrinkDetails.add(new CoffeeDrink("Americano", Beverages.DrinkSizes.SMALL, 45.00));
        listDrinkDetails.add(new CoffeeDrink("Americano", Beverages.DrinkSizes.MEDIUM, 55.00));
        listDrinkDetails.add(new CoffeeDrink("Americano", Beverages.DrinkSizes.LARGE, 65.00));
        listDrinkDetails.add(new CoffeeDrink("Espresso", Beverages.DrinkSizes.SMALL, 45.00));
        listDrinkDetails.add(new CoffeeDrink("Espresso", Beverages.DrinkSizes.MEDIUM, 55.00));
        listDrinkDetails.add(new CoffeeDrink("Espresso", Beverages.DrinkSizes.LARGE, 65.00));
        listDrinkDetails.add(new CoffeeDrink("Macchiato", Beverages.DrinkSizes.SMALL, 45.00));
        listDrinkDetails.add(new CoffeeDrink("Macchiato", Beverages.DrinkSizes.MEDIUM, 55.00));
        listDrinkDetails.add(new CoffeeDrink("Macchiato", Beverages.DrinkSizes.LARGE, 65.00));
        listDrinkDetails.add(new CoffeeDrink("Ice Coffee", Beverages.DrinkSizes.SMALL, 35.00));
        listDrinkDetails.add(new CoffeeDrink("Ice Coffee", Beverages.DrinkSizes.MEDIUM, 45.00));
        listDrinkDetails.add(new CoffeeDrink("Ice Coffee", Beverages.DrinkSizes.LARGE, 55.00));
        for (int i = 0; i < 21; i++) {
            listDrinkDetails.get(i).setDrinkId(i + 1);
        }
    }
    @Test
    public void testMenuSize() {
        // create a mock instance of JDBCUtils
        // JDBCUtils jdbcUtils = mock(JDBCUtils.class);

        // stub the listBeverageMenuDetails method to return the list of 21 items
        when(jdbcUtils.listBeverageMenuDetails()).thenReturn(listDrinkDetails);

        // test the size of the list returned by the listBeverageMenuDetails method
        assertEquals(jdbcUtils.listBeverageMenuDetails().size(), 21);
    }



    @Test
    public void testFirstDrinkId() {
        // stub the listBeverageMenuDetails method to return the list of 21 items
        when(jdbcUtils.listBeverageMenuDetails()).thenReturn(listDrinkDetails);

        // test the ID of the first item in the list returned by the listBeverageMenuDetails method
        assertEquals(jdbcUtils.listBeverageMenuDetails().get(0).getDrinkId(), 1);

    }

    @Test
    public void testFirstDrinkTitle() {
        // stub the listBeverageMenuDetails method to return the list of 21 items
        when(jdbcUtils.listBeverageMenuDetails()).thenReturn(listDrinkDetails);
        // Checking whether first drink title is Coffee
        assertEquals(jdbcUtils.listBeverageMenuDetails().get(0).getDrinkTitle(), "Coffee");
    }

    @Test
    public void testLastDrinkTitle() {
        when(jdbcUtils.listBeverageMenuDetails()).thenReturn(listDrinkDetails);
        // Checking whether last drink title is Ice coffee
        assertEquals(jdbcUtils.listBeverageMenuDetails().get(20).getDrinkTitle(), "Ice Coffee");
    }

    @Test
    public void testSecondDrinkSize() {
        when(jdbcUtils.listBeverageMenuDetails()).thenReturn(listDrinkDetails);
        // Checking whether second drink size is MEDIUM
        assertEquals(jdbcUtils.listBeverageMenuDetails().get(1).getDrinkSize(), DrinkSizes.MEDIUM);
    }

    @Test
    public void testThirdDrinkSize() {
        when(jdbcUtils.listBeverageMenuDetails()).thenReturn(listDrinkDetails);
        // Checking whether third drink size is LARGE
        assertEquals(jdbcUtils.listBeverageMenuDetails().get(2).getDrinkSize(), DrinkSizes.LARGE);
    }

    @Test
    public void testSecondDrinkPrice() {
        when(jdbcUtils.listBeverageMenuDetails()).thenReturn(listDrinkDetails);
        // Checking whether second drink price is 45.00
        assertEquals(jdbcUtils.listBeverageMenuDetails().get(1).getDrinkPrice(), 45.00, 0);
    }

    @Test
    public void checkPlaceOrderWithMock() {
        // Set up the mock to return a list with one coffee drink when listOrders is called
        List<CoffeeDrink> orders = new ArrayList<>();
        CoffeeDrink coffee = new CoffeeDrink("Coffee", DrinkSizes.SMALL, 35.00);
        orders.add(coffee);
        when(jdbcUtils.listOrders()).thenReturn(orders);

        // Call insertOrder on the mock
        jdbcUtils.insertOrder(coffee);

        // Call listOrders on the mock to get the result
        List<CoffeeDrink> result = jdbcUtils.listOrders();

        // Check that the result is as expected
        assertEquals(result.get(0).getDrinkPrice(), 35.00, 0);
        assertEquals(result.get(0).getDrinkTitle(), "Coffee");
        assertEquals(result.get(0).getDrinkSize(), DrinkSizes.SMALL);
    }
}
