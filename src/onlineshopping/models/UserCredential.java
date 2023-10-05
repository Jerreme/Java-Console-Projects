
package onlineshopping.models;

import onlineshopping.interfaces.Credential;

public class UserCredential implements Credential {
    final private String username;
    final private String password;

    public UserCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
