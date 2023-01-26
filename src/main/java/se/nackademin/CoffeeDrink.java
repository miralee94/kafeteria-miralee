package se.nackademin;

public class CoffeeDrink implements Beverages{

    private int drinkId;

    private String drinkTitle;
    private DrinkSizes drinkSize;
    private double drinkPrice;
    private DrinkAdditives drinkAdditive = DrinkAdditives.NONE;
    private DrinkSweeteners drinkSweetener = DrinkSweeteners.NONE;

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public void setDrinkAdditive(DrinkAdditives drinkAdditive) {
        this.drinkAdditive = drinkAdditive;
    }

    public void setDrinkSweetener(DrinkSweeteners drinkSweetener) {
        this.drinkSweetener = drinkSweetener;
    }

    public CoffeeDrink(String drinkTitle, DrinkSizes drinkSize, double drinkPrice) {
        this.drinkTitle = drinkTitle;
        this.drinkSize = drinkSize;
        this.drinkPrice = drinkPrice;

    }

    public int getDrinkId() {return this.drinkId;}
    public String getDrinkTitle() {return this.drinkTitle;}
    public DrinkSizes getDrinkSize() {return this.drinkSize;}
    public double getDrinkPrice() {return this.drinkPrice;}
    public DrinkAdditives getDrinkAdditives() {return this.drinkAdditive;}
    public DrinkSweeteners getDrinkSweeteners() {return this.drinkSweetener;}

    @Override
    public String toString() {
        return this.drinkId + " " +
        this.drinkTitle + " " + 
        this.drinkSize + " " + 
        this.drinkPrice + " " + 
        this.drinkAdditive + " " + 
        this.drinkSweetener;
    }
}
