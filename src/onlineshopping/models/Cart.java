package onlineshopping.models;

import java.util.ArrayList;

public class Cart {
    private static final ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<Product> getCartItems() {
        return products;
    }

    public static void setCartItems(ArrayList<Product> products) {
        Cart.products.clear();
        Cart.products.addAll(products);
    }

    public static void addToCart(Product product) {
        products.add(product);
    }

    public static int getTotalPrice() {
        int totalPrice = 0;
        for (final Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public static void emptyCart() {
        products.clear();
    }
}
