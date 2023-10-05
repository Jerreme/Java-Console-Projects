package onlineshopping.controllers;

import onlineshopping.interfaces.Credential;

import java.util.ArrayList;

public class CredentialManager {

    private static final ArrayList<Credential> credentials = new ArrayList<>();

    public void addCredential(Credential credential) {
        credentials.add(credential);
    }

    public Credential getCredential(String username, String password) {
        for (Credential credential : credentials) {
            if (credential.username().equals(username) && credential.password().equals(password)) {
                return credential;
            }
        }
        return null;
    }

    public ArrayList<Credential> getCredentials() {
        return new ArrayList<>(credentials);
    }
}
