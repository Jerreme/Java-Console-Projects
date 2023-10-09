package onlineshopping.routes;

import onlineshopping.controllers.Navigator;
import onlineshopping.controllers.ProductsManager;
import onlineshopping.controllers.Tasker;
import onlineshopping.interfaces.Route;
import onlineshopping.views.ProductsPageView;
import onlineshopping.controllers.ProductsPageController;

public class ProductsPage implements Route {

//    @Override
//    public void init() {
//        ProductsManager.setProducts(ProductsManager.generateProductsFromArray(
//                new String[]{"Milk", "Eggs", "Bread", "Cheese", "Chicken", "Beef", "Pork"},
//                new int[]{95, 50, 45, 100, 150, 200, 180}
//        ));
//        Route.super.init();
//    }

    @Override
    public void build() {
        ProductsPageView view = new ProductsPageView();
        view.showProductsPage();
        ProductsPageController controller = new ProductsPageController(view);

        Tasker tasker = new Tasker(this.toString());
        tasker.addTask(1, controller::showProducts);
        tasker.addTask(2, controller::addToCart);
        tasker.addTask(3, controller::checkout);
        tasker.addTask(0, this::back);
        tasker.runPrompt();
    }

    private void back() {
        Navigator.gotoLastRoute();
    }
}
