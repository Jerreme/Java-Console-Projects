package onlineshopping.controllers;

import onlineshopping.interfaces.Route;
import onlineshopping.interfaces.ExitCode;
import onlineshopping.views.Warn;

import java.util.HashMap;
import java.util.Map;

public class Navigator {

    private static Route lastRoute;
    private static Route currentRoute;
    private final Map<Integer, Route> routes = new HashMap<>();
    private final Map<Integer, Runnable> preCallbacks = new HashMap<>();

    public static void runRouteManually(Route route) {
        if (currentRoute != null) {
            lastRoute = currentRoute;
            currentRoute.dispose();
        }
        currentRoute = route;
        currentRoute.init();
        currentRoute.build();
    }

    public static void gotoLastRoute() {
        if (lastRoute != null) {
            currentRoute.dispose();
            currentRoute = lastRoute;
            lastRoute = null;
            currentRoute.build();
        } else {
            Warn.debugMessage("No last route found!");
            currentRoute.build();
        }
    }

    public static void reRunActiveRoute() {
        if (currentRoute != null) {
            currentRoute.dispose();
            currentRoute.init();
            currentRoute.build();
        } else {
            Warn.debugMessage("No active route found!");
        }
    }

    public void addRoute(int keyBind, Route route) {
        routes.put(keyBind, route);
    }

    public void addPreCallback(int keyBind, Runnable callback) {
        preCallbacks.put(keyBind, callback);
    }

    public void runPrompt() {
        runRoute(getInput());
    }

    private int getInput() {
        if (routes.isEmpty()) {
            Warn.debugMessageAndExit("Routes is empty!", ExitCode.EXIT_FAILURE);
        }
        System.out.print(">> ");
        try {
            return Integer.parseInt(new java.util.Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            Warn.invalidInput();
            return getInput();
        }
    }

    private void runRoute(int keyBind) {
        if (!routes.containsKey(keyBind)) {
            Warn.systemMessage("No route found!");
            currentRoute.build();
            return;
        }

        final Route route = routes.get(keyBind);
        if (route == null) {
            Warn.debugMessage("Route is null!");
            currentRoute.build();
            return;
        }

        currentRoute.dispose();
        lastRoute = currentRoute;

        if (preCallbacks.containsKey(keyBind)) {
            preCallbacks.get(keyBind).run();
        }

        currentRoute = route;
        route.init();
        route.build();
    }
}
