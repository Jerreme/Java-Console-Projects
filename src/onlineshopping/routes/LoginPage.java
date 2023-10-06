package onlineshopping.routes;

import onlineshopping.controllers.LoginController;
import onlineshopping.controllers.Navigator;
import onlineshopping.interfaces.Credential;
import onlineshopping.interfaces.Route;
import onlineshopping.models.User;
import onlineshopping.views.LoginPageView;

public class LoginPage implements Route {
    @Override
    public void build() {
        final LoginPageView view = new LoginPageView();
        final LoginController controller = new LoginController(view);
        final Credential userForLogin = controller.promptLogin();

        if (userForLogin == null) return;

        final User user = controller.submitLoginCredential(userForLogin);
        if (user == null) {
            build();
        } else {
            view.showLoginSuccess();
            Navigator.runRouteManually(new HomePage());
        }
    }
}
