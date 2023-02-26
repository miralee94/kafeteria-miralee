package se.nackademin;
/**
 * The Beverages interface defines different types of DrinkSizes, DrinkAdditives and DrinkSweeterners that can be used to customize a drink order.
 */
interface Beverages{
    enum DrinkSizes{
        SMALL,
        MEDIUM,
        LARGE
    };
    enum DrinkAdditives{
        NONE,
        MILK,
        LAKTOSFREE_MILK,
        ALMONDMILK,
    };
    enum DrinkSweeteners{
        NONE,
        SUGAR,
        BROWN_SUGAR,
        HONEY,
        STEVIA
    };

}
