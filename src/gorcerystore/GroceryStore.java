package gorcerystore;

public class GroceryStore {

    public static void main(String[] args) {
        String[] kItems = {"Milk", "Eggs", "Bread", "Cheese", "Chicken", "Beef", "Pork"};
        int[] kPrices = {95, 50, 45, 100, 150, 200, 180};

        GroceryStoreView groceryStoreView = new GroceryStoreView("ji");
        GroceryStoreController groceryStoreController = new GroceryStoreController(GroceryStoreController.generateProductsFromArray(kItems, kPrices), groceryStoreView);

        groceryStoreController.showShoppingList();

        int orderId = groceryStoreController.promptOrderId();
        int quantity = groceryStoreController.promptQuantity();

        groceryStoreController.displayOrder(orderId, quantity);
        groceryStoreController.closingSpiel();
    }
}
