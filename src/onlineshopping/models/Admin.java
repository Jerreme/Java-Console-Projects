
package onlineshopping.models;

import onlineshopping.interfaces.Credential;
import onlineshopping.interfaces.UserBalanceException;

public record Admin(String username, String password) implements Credential {

}
