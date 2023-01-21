Interface Beverages
    DrinkSizes drinkSize
    Additives additive
    Sweeteners sweetener
    boolean laktosFree

Class Coffee implements Beverages

Class Cappuccino implements Beverages

Class Latte implements Beverages

Class Americano implements Beverages

Class Espresso implements Beverages

Class Macchiato implements Beverages

Class Frappe implements Beverages

Class IceCoffee implements Beverages

Enum DrinkSizes - "SMALL", "MEDIUM", "LARGE"

Enum Additives - "MILK", "LAKTOSFREE_MILK", "ALMONDMILK"

Enum Sweeteners - "SUGAR", "BROWN_SUGAR", "HONEY", "STEVIA"

Class MainMenu
String MainMenu - "Make your choice from 1-3
1. View beverage menu
2. Place order
    "Choose a beverage":
    1. Coffee
        - Enter drink size
        - Enter additive
        - Enter sweetener
    2. Cappuccino
        - Enter drink size
        - Laktosfree?
        - Enter sweetener
    3. Latte
        - Enter drink size
        - Laktosfree?
        - Enter sweetener
    4. Americano
        - Enter drink size
        - Enter additive
        - Enter sweetener
    5. Espresso
        - Enter drink size
        - Enter additive
        - Enter sweetener
    6. Macchiato
        - Enter drink size
        - Enter additive
        - Laktosfree?
        - Enter sweetener
    7. Frappe
        - Enter drink size
        - Enter additive
        - Laktosfree?
        - Enter sweetener
    8. Ice coffee
        - Enter drink size
        - Enter additive
        - Enter sweetener

    "Done? Y or N"
    Y - Saving order... Back to MainMenu
3. View orders


