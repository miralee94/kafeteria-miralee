package se.nackademin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.List;
import java.util.Scanner;

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

        // Checking whether last drink title is Ice coffee
        assertEquals(listDrinkDetails.get(20).getDrinkTitle(),"Ice Coffee");

        // Checking whether second  drink size is MEDIUM
        assertEquals(listDrinkDetails.get(1).getDrinkSize(), DrinkSizes.MEDIUM);

        // Checking whether 3th drink size is LARGE
        assertEquals(listDrinkDetails.get(2).getDrinkSize(), DrinkSizes.LARGE);

        // Checking whether second  drink price is 45.00
        assertEquals(listDrinkDetails.get(1).getDrinkPrice(),45.00, 0);

    }
    //Making an order and check my list orders
    @Test
    public void checkPlaceOrder() {
        JDBCUtilsObject.connectToDatabase();
        JDBCUtilsObject.insertOrder(new CoffeeDrink("Coffee", DrinkSizes.MEDIUM, 45.00));
        List<CoffeeDrink>orders = JDBCUtilsObject.listOrders();
        //Cheking last (12th in my case) drinks price
        assertEquals(orders.get(18).getDrinkPrice(), 45.00, 0);

        //Cheking last drinks title
        assertEquals(orders.get(18).getDrinkTitle(), "Coffee");

        //Cheking last drinks size
        assertEquals(orders.get(18).getDrinkSize(), DrinkSizes.MEDIUM);
}}
