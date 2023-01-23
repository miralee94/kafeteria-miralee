package se.nackademin;

public class CoffeeDrink implements Beverages{

    private int drinkId;
    
    private String drinkTitle;
    private DrinkSizes drinkSize;
    private double drinkPrice;
    private DrinkAdditives drinkAdditive;
    private DrinkSweeteners drinkSweetener;

    public CoffeeDrink(String drinkTitle, DrinkSizes drinkSize, double drinkPrice) {
        this.drinkTitle = drinkTitle;
        this.drinkSize = drinkSize;
        this.drinkPrice = drinkPrice;

    }
    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }
    public int getDrinkId() {return this.drinkId;}
    public String getTitles() {return this.drinkTitle;}
    public DrinkSizes getDrinksSize() {return this.drinkSize;}
    public double getDrinksPrice() {return this.drinkPrice;}
    public DrinkAdditives getDrinkAdditives() {return this.drinkAdditive;}
    public DrinkSweeteners getDrinkSweeteners() {return this.drinkSweetener;}
}
