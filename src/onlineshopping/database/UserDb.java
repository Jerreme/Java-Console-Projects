package onlineshopping.database;

import onlineshopping.interfaces.Credential;
import onlineshopping.models.User;
import onlineshopping.views.Warn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDb {
    private final Connection conn;

    public UserDb() {
        this.conn = DatabaseHandler.getConnection();
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users(username, password, balance) VALUES(?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.username());
            pstmt.setString(2, user.password());
            pstmt.setInt(3, user.balance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Warn.debugMessage(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }

    public User getUser(Credential credential) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, credential.username());
            pstmt.setString(2, credential.password());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("balance")
                );
            }
        } catch (SQLException e) {
            Warn.debugMessage(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
        return user;
    }

    public boolean isUserAlreadyExist(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        boolean exists = true;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
        } catch (SQLException e) {
            Warn.debugMessage(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
        return exists;
    }

    public void updateUserBalance(User user) {
        String sql = "UPDATE users SET balance = ? WHERE username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.balance());
            pstmt.setString(2, user.username());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Warn.debugMessage(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }
}
