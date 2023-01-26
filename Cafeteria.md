## Cafeteria

1. The program is run from the menu
2. First option creates mysql db, 2 tables menu and orders, assuming there is a db running via docker on localhost. To check the db, you can, for example, use the mysql extension in vscode
3. Second option will show the menu on your terminal
4. Third option allows you to place an order, by entering the drink name and size. Some drinks, but not all, have additives and sweeteners. Only one drink can be added at a time.
5. If you press 4, you can see added orders. Most recent ends up at the end of the list automatically.

## Tests

To run tests, you can e.g. run command

```mvn test ```

 in terminal.