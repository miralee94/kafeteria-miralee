package se.nackademin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.List;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JDBCUtilstest {

    JDBCUtils JDBCUtilsObject = new JDBCUtils("127.0.0.1", "3306");
    @Test
    public void listDrinks() {
        JDBCUtilsObject.connectToDatabase();

        List<CoffeeDrink> listDrinkDetails = JDBCUtilsObject.listCoffeeDrinksDetails();
        // Checking whether totally 2 freelancers are available
        assertEquals(listDrinkDetails.size(), 0);
        // Checking whether first freelancer id is 1
        assertEquals(listDrinkDetails.get(0).getDrinkId(),1);
        // Checking whether first freelancer name is Freelancer A
        assertEquals(listDrinkDetails.get(0).getDrinkTitle(),"Coffee");
        // Checking whether second  freelancer age is 20
        assertEquals(listDrinkDetails.get(1).getDrinkSize(),"MEDIUM");
        // Checking whether second  freelancer price per hour  is 2000
        assertEquals(listDrinkDetails.get(1).getDrinkPrice(),45.00, 0);
    }

}
