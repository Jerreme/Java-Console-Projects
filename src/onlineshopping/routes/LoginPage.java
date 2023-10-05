package onlineshopping.routes;

import onlineshopping.controllers.AuthenticationController;
import onlineshopping.interfaces.Credential;
import onlineshopping.interfaces.Route;
import onlineshopping.models.LoginCredential;
import onlineshopping.views.Authentication;

public class LoginPage implements Route {
    @Override
    public void build() {
        final Authentication authView = new Authentication();
        final AuthenticationController authController = new AuthenticationController(authView);
        final LoginCredential userForLogin = authController.promptLogin();

        if (userForLogin == null) return;

        final Credential user = authController.submitLoginCredential(userForLogin);
        if (user == null) {
            build();
        } else {
            // TODO goto shopping screen
            System.out.println("Login success");
        }
    }
}
