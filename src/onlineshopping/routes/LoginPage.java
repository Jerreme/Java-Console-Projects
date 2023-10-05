package onlineshopping.routes;

import onlineshopping.controllers.LoginController;
import onlineshopping.interfaces.Credential;
import onlineshopping.interfaces.Route;
import onlineshopping.models.LoginCredential;
import onlineshopping.views.LoginPageView;

public class LoginPage implements Route {
    @Override
    public void build() {
        final LoginPageView view = new LoginPageView();
        final LoginController controller = new LoginController(view);
        final LoginCredential userForLogin = controller.promptLogin();

        if (userForLogin == null) return;

        final Credential user = controller.submitLoginCredential(userForLogin);
        if (user == null) {
            build();
        } else {
            // TODO goto shopping screen
            System.out.println("Login success");
        }
    }
}
