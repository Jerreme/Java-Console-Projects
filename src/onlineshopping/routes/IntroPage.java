package onlineshopping.routes;

import onlineshopping.controllers.Navigator;
import onlineshopping.interfaces.Route;
import onlineshopping.views.IntroPageView;

public class IntroPage implements Route {

    @Override
    public void build() {
        final IntroPageView authView = new IntroPageView();
        authView.showAuthScreen();

        final Navigator navigator = new Navigator();
        navigator.addRoute(1, new LoginPage());
        navigator.addRoute(2, new RegistrationPage());
        navigator.addRoute(0, exitRoute);
        navigator.runPrompt();
    }

    Route exitRoute = () -> {
        final IntroPageView authView = new IntroPageView();
        authView.showOutro();
        System.exit(0);
    };
}

