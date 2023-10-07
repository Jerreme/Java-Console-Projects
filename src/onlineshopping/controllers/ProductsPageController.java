package onlineshopping.controllers;

import onlineshopping.interfaces.MoneyException;
import onlineshopping.interfaces.ExitCode;
import onlineshopping.models.Cart;
import onlineshopping.models.Product;
import onlineshopping.models.User;
import onlineshopping.views.ProductsPageView;
import onlineshopping.views.Warn;

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
            view.showAddedToCart(product);
            Cart.addToCart(product);
        }
        Navigator.reRunActiveRoute();
    }

    public void checkout() {
        ArrayList<Product> cartItems = Cart.getCartItems();
        if (cartItems.isEmpty()) {
            view.showEmptyCart();
            Navigator.reRunActiveRoute();
            return;
        }
        view.showCartItems(cartItems);
        final int balance = new CredentialManager().getLoggedInUser().getWalletBalance();
        view.showWalletBalance(balance);
        view.showCartOptions();

        Tasker tasker = new Tasker(this.toString());
        tasker.addTask(1, this::proceedCheckout);
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

    private void proceedCheckout() {
        CredentialManager login = new CredentialManager();
        final User currentUser = login.getLoggedInUser();
        if (currentUser == null) {
            Warn.debugMessageAndExit("No user logged in!", ExitCode.EXIT_FAILURE);
            return;
        }

        try {
            final int totalPrice = Cart.getTotalPrice();
            login.updateUser(currentUser.spend(totalPrice));
            Cart.emptyCart();
            view.systemMessage("Products has been checked out ✓");
            final User updatedUser = login.getLoggedInUser();
            final int newBalance = updatedUser.getWalletBalance();
            view.showWalletBalance(newBalance);
            // TODO add to list of completed orders
        } catch (MoneyException e) {
            view.warnInsufficientMoney(e.getMessage());
        }
    }

    private void emptyCart() {
        Cart.emptyCart();
        view.systemMessage("Cart has been emptied ✓");
    }

}
