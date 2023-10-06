package onlineshopping.controllers;

import onlineshopping.models.Product;

import java.util.ArrayList;

public class ProductsManager {

    private static ArrayList<Product> products;

    public static ArrayList<Product> generateProductsFromArray(String[] productNames, int[] productPrices) {
        final ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < productNames.length; i++) {
            products.add(new Product(i + 1, productNames[i], productPrices[i]));
        }
        return products;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        ProductsManager.products = products;
    }

    public static Product getProductById(int id) {
        for (Product product : products) {
            if (product.getKey() == id) return product;
        }
        return null;
    }
}
