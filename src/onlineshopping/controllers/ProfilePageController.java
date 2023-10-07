package onlineshopping.controllers;

import onlineshopping.interfaces.MoneyException;
import onlineshopping.models.User;
import onlineshopping.views.ProfilePageView;
import onlineshopping.views.Warn;

import java.util.Scanner;

public class ProfilePageController {
    private final ProfilePageView view;

    public ProfilePageController(ProfilePageView view) {
        this.view = view;
    }

    public void cashIn() {
        int amount = askForAmount();
        CredentialManager credentialManager = new CredentialManager();
        try {
            final User newUser = credentialManager.getLoggedInUser().deposit(amount);
            credentialManager.updateUser(newUser);
            view.showSuccessDepositMessage(amount);
        } catch (MoneyException e) {
            view.showInvalidAmountMessage(User.MINIMUM_CASH_IN_AMOUNT, User.MAXIMUM_CASH_IN_AMOUNT);
        }
        Navigator.reRunActiveRoute();
    }

    private int askForAmount() {
        view.showCashInPage(User.MINIMUM_CASH_IN_AMOUNT, User.MAXIMUM_CASH_IN_AMOUNT);
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            Warn.invalidInput();
            return askForAmount();
        }
    }
}
