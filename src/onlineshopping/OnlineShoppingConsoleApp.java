package onlineshopping;

import onlineshopping.controllers.AuthenticationController;
import onlineshopping.controllers.Navigator;
import onlineshopping.interfaces.Credential;
import onlineshopping.models.LoginCredential;
import onlineshopping.models.RegistrationCredential;
import onlineshopping.views.Authentication;

public class OnlineShoppingConsoleApp {

    public static void main(String[] args) {
        Navigator.runInitialRoute(OnlineShoppingConsoleApp::gotoAuthScreen);
    }

    static void gotoAuthScreen() {
        Authentication authView = new Authentication();
        authView.showAuthScreen();

        Navigator navigator = new Navigator();
        navigator.addRoute(1, () -> gotoLoginScreen(authView));
        navigator.addRoute(2, () -> gotoRegistrationScreen(authView));
        navigator.addRoute(0, () -> System.exit(0));
        navigator.runPrompt();
    }

    static void gotoLoginScreen(Authentication authView) {
        AuthenticationController authController = new AuthenticationController(authView);
        final LoginCredential userForLogin = authController.promptLogin();

        final Credential user = authController.submitLoginCredential(userForLogin);
        if (user == null) {
            gotoLoginScreen(authView);
        } else {
            // TODO goto shopping screen
        }
    }

    static void gotoRegistrationScreen(Authentication authView) {
        AuthenticationController authController = new AuthenticationController(authView);
        final RegistrationCredential userForRegistration = authController.promptRegistration();

        final Credential user = authController.submitRegistrationCredential(userForRegistration);
        if (user == null) {
            gotoRegistrationScreen(authView);
        } else {
            Navigator.gotoLastRoute();
        }
    }
}
