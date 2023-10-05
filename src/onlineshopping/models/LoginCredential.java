package onlineshopping.models;

import onlineshopping.interfaces.Credential;

public record LoginCredential(String username, String password) implements Credential {
}
