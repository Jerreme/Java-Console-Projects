package onlineshopping;

import onlineshopping.controllers.Navigator;
import onlineshopping.database.DatabaseHandler;
import onlineshopping.routes.IntroPage;

public class OnlineShoppingConsoleApp {

    public static void main(String[] args) {
        initDatabaseTables();
        Navigator.runRouteManually(new IntroPage());
    }

    private static void initDatabaseTables() {
        DatabaseHandler.createUserTable();
        DatabaseHandler.createCartTable();
        DatabaseHandler.createProductTable();
        DatabaseHandler.createPurchasedTable();
    }

}
