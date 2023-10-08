package onlineshopping.controllers;

import onlineshopping.database.PurchasedDb;
import onlineshopping.interfaces.ExitCode;
import onlineshopping.interfaces.UserBalanceException;
import onlineshopping.models.CartManager;
import onlineshopping.models.Product;
import onlineshopping.models.PurchasedLog;
import onlineshopping.models.User;
import onlineshopping.views.ProductsPageView;
import onlineshopping.views.Warn;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsPageController {

    private final ProductsPageView view;

    public ProductsPageController(ProductsPageView view) {
        this.view = view;
    }

    public void showProducts() {
        view.showProducts(ProductsManager.getProducts());
        Navigator.reRunActiveRoute();
    }

    public void addToCart() {
        int productId = promptProductId();
        if (productId == 0) {
            Navigator.reRunActiveRoute();
            return;
        }

        final Product product = ProductsManager.getProductById(productId);
        if (product == null) {
            view.showProductNotFound();
        } else {
            final String username = CredentialManager.getLoggedInUser().username();
            CartManager.addToCart(username, product);
            view.showAddedToCart(product);
        }
        Navigator.reRunActiveRoute();
    }

    public void checkout() {
        final String username = CredentialManager.getLoggedInUser().username();
        ArrayList<Product> cartItems = CartManager.getCartItems(username);
        if (cartItems.isEmpty()) {
            view.showEmptyCart();
            Navigator.reRunActiveRoute();
            return;
        }
        view.showCartItems(cartItems);
        final int balance = CredentialManager.getLoggedInUser().getWalletBalance();
        view.showWalletBalance(balance);
        view.showCartOptions();

        Tasker tasker = new Tasker(this.toString());
        tasker.addTask(1, this::placeOrder);
        tasker.addTask(2, this::emptyCart);
        tasker.addTask(0, null);
        tasker.runPrompt();
        Navigator.reRunActiveRoute();
    }

    private int promptProductId() {
        final Scanner scanner = new Scanner(System.in);
        view.askForOrder(ProductsManager.getProducts().size());
        int productId;
        try {
            productId = scanner.nextInt();
        } catch (Exception e) {
            Warn.invalidInput();
            productId = promptProductId();
        }
        return productId;
    }

    private void placeOrder() {
        final User currentUser = CredentialManager.getLoggedInUser();
        if (currentUser == null) {
            Warn.debugMessageAndExit("No user logged in!", ExitCode.EXIT_FAILURE);
            return;
        }

        try {
            // update user balance
            final int totalPrice = CartManager.getTotalPrice();
            CredentialManager.updateUser(currentUser.spend(totalPrice));
            view.systemMessage("Products has been checked out ✓");

            final int newBalance = CredentialManager.getLoggedInUser().getWalletBalance();
            view.showWalletBalance(newBalance);

            // Get current date
            final Date date = new Date();
            final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final String dateString = formatter.format(date);

            // Create purchased log
            final PurchasedLog purchase = new PurchasedLog(
                    currentUser.username(),
                    CartManager.getCartItems(currentUser.username()),
                    dateString
            );

            // add to db of completed orders
            final PurchasedDb db = new PurchasedDb();
            db.addPurchased(purchase);

            // finally empty the cart
            CartManager.emptyCart();
        } catch (UserBalanceException e) {
            view.warnInsufficientMoney(e.getMessage());
        }
    }

    private void emptyCart() {
        CartManager.emptyCart();
        view.systemMessage("Cart has been emptied ✓");
    }

}
