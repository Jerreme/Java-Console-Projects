package onlineshopping.views;

import onlineshopping.interfaces.Messenger;
import onlineshopping.models.User;

public class ProfilePageView extends Messenger {

    public void showProfilePage(User user) {
        printHeader("User Profile");
        println("username: " + user.username());
        println("password: " + user.password());
        println("E-Wallet");
        println("balance: ₱" + user.money());
    }

    public void showProfileOptions() {
        println("[1] Cash In");
        println("[0] Back");
    }

    public void showCashInPage(int minimumCashInAmount, int maximumCashInAmount) {
        printHeader("Cash In");
        println("Enter amount [₱" + minimumCashInAmount + " - ₱" + maximumCashInAmount + "]");
        print(">> ₱");
    }

    public void showInvalidAmountMessage(int minimumCashInAmount, int maximumCashInAmount) {
        systemMessage("Please enter an amount between ₱" + minimumCashInAmount + " and ₱" + maximumCashInAmount + ".");
    }

    public void showSuccessDepositMessage(int amount) {
        systemMessage(String.format("%d has been deposited to your account.", amount));
    }
}
