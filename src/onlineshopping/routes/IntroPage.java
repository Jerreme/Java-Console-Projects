package onlineshopping.routes;

import onlineshopping.controllers.Navigator;
import onlineshopping.interfaces.Route;
import onlineshopping.views.Authentication;

public class IntroPage implements Route {

    @Override
    public void build() {
        final Authentication authView = new Authentication();
        authView.showAuthScreen();

        final Navigator navigator = new Navigator();
        navigator.addRoute(1, new LoginPage());
        navigator.addRoute(2, new RegistrationPage());
        navigator.addRoute(0, exitRoute);
        navigator.runPrompt();
    }

    Route exitRoute = () -> {
        final Authentication authView = new Authentication();
        authView.showAuthScreen();
        System.exit(0);
    };
}

