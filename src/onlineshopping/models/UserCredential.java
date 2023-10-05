
package onlineshopping.models;

import onlineshopping.interfaces.Credential;

public record UserCredential(String username, String password) implements Credential {
}
