package se.nackademin;

//Class CoffeeDrink for all the drinks in the menu
public class CoffeeDrink implements Beverages{

    private int drinkId;
    private String drinkTitle;
    private DrinkSizes drinkSize;
    private double drinkPrice;
    //Default values
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

    //Construktor with mandatory values
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

    //Print this format of CoffeeDrink
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
