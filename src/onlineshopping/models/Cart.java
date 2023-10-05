package onlineshopping.models;

import java.util.ArrayList;

public class Cart {
    private final ArrayList<Product> products;

    public Cart(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getProductsLength() {
        return products.size();
    }
}
