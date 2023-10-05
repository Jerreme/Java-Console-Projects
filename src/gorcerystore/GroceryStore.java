package gorcerystore;

import onlineshopping.controllers.OnlineShoppingController;
import onlineshopping.views.OnlineShoppingView;

public class GroceryStore {

    public static void main(String[] args) {
        String[] kItems = {"Milk", "Eggs", "Bread", "Cheese", "Chicken", "Beef", "Pork"};
        int[] kPrices = {95, 50, 45, 100, 150, 200, 180};

        OnlineShoppingView onlineShoppingView = new OnlineShoppingView("ji");
        OnlineShoppingController onlineShoppingController = new OnlineShoppingController(OnlineShoppingController.generateProductsFromArray(kItems, kPrices), onlineShoppingView);

        onlineShoppingController.showShoppingList();

        int orderId = onlineShoppingController.promptOrderId();
        int quantity = onlineShoppingController.promptQuantity();

        onlineShoppingController.displayOrder(orderId, quantity);
        onlineShoppingController.closingSpiel();
    }
}
