package onlineshopping.views;

import onlineshopping.interfaces.Messenger;
import onlineshopping.models.Product;

import java.util.ArrayList;

public class ProductsPageView extends Messenger {
    public void showProductsPage() {
        println("\n-------- Buy --------");
        println("[1] Show Products");
        println("[2] Add To Cart");
        println("[2] Checkout");
        println("[0] Back");
    }

    public void showProducts(ArrayList<Product> products) {
        if (products.isEmpty()) {
//            warnForEmptyProducts();
            return;
        }

        println("\n-------- List of Products --------");
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
        println("\n-------- Add to Cart --------");
        print(productsCount > 0 ? String.format("[%d-%d]", 1, productsCount) : "[Empty]");
        println(" Choose product");
        println("[0] Cancel");
        print(">> ");
    }

    public void showProductNotFound() {
        systemMessage("Product not found!");
    }

    public void showAddedToCart(Product product) {
        systemMessage(String.format("%s has been added to cart ✓", product.getProductName()));
    }
}
