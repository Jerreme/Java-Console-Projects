package onlineshopping.routes;

import onlineshopping.controllers.CredentialManager;
import onlineshopping.controllers.Navigator;
import onlineshopping.controllers.ProfilePageController;
import onlineshopping.controllers.Tasker;
import onlineshopping.interfaces.Route;
import onlineshopping.views.ProfilePageView;


public class ProfilePage implements Route {
    @Override
    public void build() {
        ProfilePageView view = new ProfilePageView();
        view.showProfilePage(CredentialManager.getLoggedInUser());
        view.printDashSeparator();
        view.showProfileOptions();
        ProfilePageController controller = new ProfilePageController(view);
        Tasker tasker = new Tasker(this.toString());
        tasker.addTask(1, controller::cashIn);
        tasker.addTask(0, Navigator::gotoLastRoute);
        tasker.runPrompt();
    }
}
