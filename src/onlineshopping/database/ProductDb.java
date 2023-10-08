package onlineshopping.database;

import onlineshopping.models.Product;
import onlineshopping.views.Warn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDb {

    final private Connection conn;

    public ProductDb() {
        this.conn = DatabaseHandler.getConnection();
    }

    public void addProducts(ArrayList<Product> products) {
        String sql = "INSERT INTO products(product_key, product_name, product_price) VALUES(?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Product product : products) {
                pstmt.setInt(1, product.getKey());
                pstmt.setString(2, product.getProductName());
                pstmt.setInt(3, product.getPrice());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            Warn.debugMessage(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO products(product_name, product_price) VALUES(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Warn.debugMessage(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }

    public ArrayList<Product> getProducts() {
        String sql = "SELECT * FROM products";
        ArrayList<Product> products = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("product_key"),
                        rs.getString("product_name"),
                        rs.getInt("product_price")
                ));
            }
        } catch (SQLException e) {
            Warn.debugMessage(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
        return products;
    }
}
