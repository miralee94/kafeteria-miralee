package se.nackademin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.List;
import org.junit.Test;

import se.nackademin.Beverages.DrinkSizes;

import java.util.ArrayList;
import java.util.List;

public class JDBCUtilsTest {

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

        // Checking whether second  drink size is MEDIUM
        assertEquals(listDrinkDetails.get(1).getDrinkSize(), DrinkSizes.MEDIUM);

        // Checking whether second  drink price is 45.00
        assertEquals(listDrinkDetails.get(1).getDrinkPrice(),45.00, 0);
    }

}
