package onlineshopping.controllers;

import onlineshopping.interfaces.Route;
import onlineshopping.views.Warn;

import java.util.HashMap;
import java.util.Map;

public class Navigator {

    private Map<Integer, Route> mapOfRoutes = new HashMap<>();
    private static Route lastRoute;
    private static Route currentRoute;

    public static void runInitialRoute(Route route) {
        currentRoute = route;
        route.build();
    }

    public static void gotoLastRoute() {
        if (lastRoute != null) {
            currentRoute = lastRoute;
            lastRoute.build();
        } else {
            Warn.debugMessageAndExit("No last route found!", -1);
        }
    }

    public void addRoute(int keyBind, Route route) {
        mapOfRoutes.put(keyBind, route);
    }

    public void runPrompt() {
        int keyBind = getInput();
        runRoute(keyBind);
    }

    private int getInput() {
        System.out.print(">> ");

        if (mapOfRoutes.isEmpty()) {
            Warn.debugMessageAndExit("Routes is empty!", -1);
        }

        try {
            return Integer.parseInt(new java.util.Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            Warn.invalidInput();
            return getInput();
        }
    }

    private void runRoute(int keyBind) {
        Route route = mapOfRoutes.get(keyBind);
        if (route == null) {
            Warn.debugMessageAndExit("No routes found!", -1);
        } else {
            lastRoute = currentRoute;
            currentRoute = route;
            route.build();
        }
    }
}
