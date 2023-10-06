package onlineshopping;

import onlineshopping.controllers.Navigator;
import onlineshopping.routes.IntroPage;

public class OnlineShoppingConsoleApp {

    public static void main(String[] args) {
        Navigator.runRouteManually(new IntroPage());
    }

}
