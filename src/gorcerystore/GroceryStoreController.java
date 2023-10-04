package gorcerystore;

import java.util.ArrayList;
import java.util.Scanner;

class GroceryStoreController {
    private final ArrayList<Product> shoppingList;
    private final GroceryStoreView groceryStoreView;

    public GroceryStoreController(ArrayList<Product> shoppingList, GroceryStoreView groceryStoreView) {
        this.shoppingList = shoppingList;
        this.groceryStoreView = groceryStoreView;
    }

    public static ArrayList<Product> generateProductsFromArray(String[] productNames, int[] productPrices) {
        final ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < productNames.length; i++) {
            products.add(new Product(i, productNames[i], productPrices[i]));
        }
        return products;
    }

    public void showShoppingList() {
        groceryStoreView.showShoppingList(shoppingList);
    }

    public void closingSpiel() {
        groceryStoreView.closingSpiel();
    }

    public int promptOrderId() {
        int toBuy = 0;
        do {
            final Scanner scanner = new Scanner(System.in);
            groceryStoreView.askForOrder(shoppingList.size());

            try {
                toBuy = scanner.nextInt();
            } catch (Exception e) {
                groceryStoreView.warnForInvalidInput();
            }
        } while (toBuy == 0);
        return toBuy;
    }

    public int promptQuantity() {
        int quantity = 0;
        do {
            final Scanner scanner = new Scanner(System.in);
            groceryStoreView.askForQuantity();

            try {
                quantity = scanner.nextInt();
            } catch (Exception e) {
                groceryStoreView.warnForInvalidInput();
            }
        } while (quantity == 0);
        return quantity;
    }

    public void displayOrder(int orderId, int quantity) {
        // Guard Clause
        if (orderId == 0 || quantity == 0) {
            groceryStoreView.warnForInvalidOrder();
            return;
        }
        groceryStoreView.displayOrder(orderId, quantity, shoppingList);
    }

}