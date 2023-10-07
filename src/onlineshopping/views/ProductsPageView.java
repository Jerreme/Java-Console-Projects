package onlineshopping.views;

import onlineshopping.interfaces.Messenger;
import onlineshopping.models.Product;

import java.util.ArrayList;

public class ProductsPageView extends Messenger {
    public void showProductsPage() {
        printHeader("Buy");
        println("[1] Show Products");
        println("[2] Add To Cart");
        println("[3] Check Out");
        println("[0] Back");
    }

    public void showProducts(ArrayList<Product> products) {
        if (products.isEmpty()) {
            Warn.debugMessage("No products found!");
            return;
        }

        printHeader("List of Products");
        int i = 1;
        for (Product product : products) {
            String formatted = String.format("[%s] %s ₱%s", product.getKey(), product.getProductName(), product.getPrice());
            if (i % 3 == 0) println(formatted);
            else print(formatted + "\t\t");
            i++;
        }

        println("");
    }

    public void askForOrder(int productsCount) {
        printHeader("Add to Cart");
        print(productsCount > 0 ? String.format("[%d-%d]", 1, productsCount) : "[Empty]");
        println(" Choose Product");
        println("[0] Cancel");
        print(">> ");
    }

    public void showProductNotFound() {
        systemMessage("Product not found!");
    }

    public void showAddedToCart(Product product) {
        systemMessage(String.format("%s has been added to cart ✓", product.getProductName()));
    }

    private String generateSpaces(int nameLength) {
        final int MAX_LENGTH = 23;
        final int length = MAX_LENGTH - nameLength;
        return " ".repeat(Math.max(0, length));
    }

    public void showCartItems(ArrayList<Product> cartItems) {
        printHeader("Cart Items");
        int totalPrice = 0;
        for (Product product : cartItems) {
            totalPrice += product.getPrice();
            final String name = product.getProductName();
            println(String.format("· %s%s₱%s", name, generateSpaces(name.length()), product.getPrice()));
        }
        showTotalPrice(totalPrice);
        printDashSeparator();
    }

    public void showTotalPrice(int totalPrice) {
        final String label = "Total Price:";
        println(String.format("%s%s  ₱%s", label, generateSpaces(label.length()), totalPrice));
    }

    public void showEmptyCart() {
        systemMessage("Cart is empty!");
    }

    public void warnInsufficientMoney(String remainingMoney) {
        systemMessage("Insufficient funds!");
        systemMessage(remainingMoney);
    }

    public void showCartOptions() {
        println("[1] Place Order");
        println("[2] Empty Cart");
        println("[0] Back");
    }

    public void showWalletBalance(int walletBalance) {
        final String label = "E-Wallet Balance:";
        println(String.format("%s%s  ₱%s", label, generateSpaces(label.length()), walletBalance));
    }


}
