
package onlineshopping.models;

import onlineshopping.interfaces.Credential;
import onlineshopping.interfaces.MoneyException;

public record User(String username, String password, int money) implements Credential {
    public static final int DEFAULT_MONEY = 1000;
    public static final int MINIMUM_CASH_IN_AMOUNT = 100;
    public static final int MAXIMUM_CASH_IN_AMOUNT = 3000;

    public User spend(int amount) throws MoneyException {
        if (amount > money) throw new MoneyException("Your E-Wallet: ₱" + money);
        return new User(username, password, money - amount);
    }

    public User deposit(int amount) throws MoneyException {
        if (amount < MINIMUM_CASH_IN_AMOUNT || amount > MAXIMUM_CASH_IN_AMOUNT) {
            throw new MoneyException("Invalid Amount");
        }
        return new User(username, password, money + amount);
    }

    public int getWalletBalance() {
        return money;
    }
}
