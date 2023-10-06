
package onlineshopping.models;

import onlineshopping.interfaces.Credential;

public record User(String username, String password) implements Credential {
}
