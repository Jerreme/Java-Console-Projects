package onlineshopping.models;

import onlineshopping.interfaces.Credential;

public record RegistrationCredential(String username, String password) implements Credential {
}
