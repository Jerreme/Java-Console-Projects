package onlineshopping.routes;

import onlineshopping.controllers.CredentialManager;
import onlineshopping.controllers.Navigator;
import onlineshopping.interfaces.Route;
import onlineshopping.views.HomePageView;

public class HomePage implements Route {

    @Override
    public void init() {
        Route.super.init();
        CredentialManager credentialManager = new CredentialManager();
        String username = credentialManager.getUser().username();
        final HomePageView view = new HomePageView();
        view.welcomeUser(username);
    }

    @Override
    public void build() {
        final HomePageView view = new HomePageView();
        view.showAuthScreen();

        final Navigator navigator = new Navigator();
        navigator.addRoute(1, new ProductsPage());
        navigator.addRoute(2, null);
        navigator.addRoute(3, null);
        navigator.addRoute(0, new IntroPage());
        navigator.addPreCallback(0, this::logout);
        navigator.runPrompt();
    }

    private void logout() {
        CredentialManager.logout();
        final HomePageView view = new HomePageView();
        view.showLogoutMessage();
    }

}
