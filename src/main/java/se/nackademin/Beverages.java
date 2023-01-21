package se.nackademin;

interface Beverages{
    enum DrinkSizes{
        SMALL,
        MEDIUM,
        LARGE
    };
    enum Additives{
        MILK,
        LAKTOSFREE_MILK,
        ALMONDMILK,
    };
    enum Sweeteners{
        SUGAR,
        BROWN_SUGAR,
        HONEY,
        STEVIA
    };
    public boolean laktosfree();
}
