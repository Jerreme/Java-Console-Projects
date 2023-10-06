package onlineshopping.controllers;

import onlineshopping.models.Cart;
import onlineshopping.models.Product;
import onlineshopping.views.ProductsPageView;
import onlineshopping.views.Warn;

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
            Navigator.reRunActiveRoute();
        } else {
            view.showAddedToCart(product);
            Cart.addToCart(product);
            Navigator.reRunActiveRoute();
        }
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

}
