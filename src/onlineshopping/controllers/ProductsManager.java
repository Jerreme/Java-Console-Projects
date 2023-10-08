package onlineshopping.controllers;

import onlineshopping.database.ProductDb;
import onlineshopping.models.Product;

import java.util.ArrayList;

public class ProductsManager {

    private static final ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<Product> generateProductsFromArray(String[] productNames, int[] productPrices) {
        final ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < productNames.length; i++) {
            products.add(new Product(i + 1, productNames[i], productPrices[i]));
        }
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        final ProductDb productDb = new ProductDb();
        if (!productDb.getProducts().isEmpty()) return;
        final ProductDb productDb1 = new ProductDb();
        productDb1.addProducts(products);
    }

    public static ArrayList<Product> getProducts() {
        if (!ProductsManager.products.isEmpty()) return ProductsManager.products;
        final ProductDb productDb = new ProductDb();
        ProductsManager.products.clear();
        ProductsManager.products.addAll(productDb.getProducts());
        return ProductsManager.products;
    }

    public static Product getProductById(int id) {
        for (Product product : products) {
            if (product.getKey() == id) return product;
        }
        return null;
    }
}
