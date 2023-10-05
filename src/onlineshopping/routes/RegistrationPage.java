package onlineshopping.routes;

import onlineshopping.controllers.AuthenticationController;
import onlineshopping.controllers.Navigator;
import onlineshopping.interfaces.Credential;
import onlineshopping.interfaces.Route;
import onlineshopping.models.RegistrationCredential;
import onlineshopping.views.Authentication;

public class RegistrationPage implements Route {
    @Override
    public void build() {
        final Authentication authView = new Authentication();
        final AuthenticationController authController = new AuthenticationController(authView);
        final RegistrationCredential userForRegistration = authController.promptRegistration();

        if (userForRegistration == null) return;
        
        final Credential user = authController.submitRegistrationCredential(userForRegistration);
        if (user == null) {
            build();
        } else {
            Navigator.gotoLastRoute();
        }
    }
}
