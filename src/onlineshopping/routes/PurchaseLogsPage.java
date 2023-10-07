package onlineshopping.routes;

import onlineshopping.controllers.PurchaseLogsController;
import onlineshopping.interfaces.Route;
import onlineshopping.views.PurchaseLogsView;

public class PurchaseLogsPage implements Route {

    @Override
    public void build() {
        PurchaseLogsView view = new PurchaseLogsView();
        PurchaseLogsController controller = new PurchaseLogsController(view);
        controller.showPurchasedLogs();
    }
}
