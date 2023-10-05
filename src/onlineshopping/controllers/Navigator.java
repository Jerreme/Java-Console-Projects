package onlineshopping.controllers;

import onlineshopping.views.Warn;

import java.util.HashMap;
import java.util.Map;

public class Navigator {

    private Map<Integer, Runnable> mapOfRoutes = new HashMap<>();
    private static Runnable lastRoute;
    private static Runnable currentRoute;

    public static void runInitialRoute(Runnable runnable) {
        currentRoute = runnable;
        runnable.run();
    }

    public static void gotoLastRoute() {
        if (lastRoute != null) {
            currentRoute = lastRoute;
            lastRoute.run();
        } else {
            Warn.debugMessageAndExit("No last route found!", -1);
        }
    }

    public void addRoute(int keyBind, Runnable runnable) {
        mapOfRoutes.put(keyBind, runnable);
    }

    public void runPrompt() {
        int keyBind = getInput();
        if (keyBind == 0 && lastRoute != null) {
            lastRoute.run();
            return;
        }
        run(keyBind);
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

    private void run(int keyBind) {
        Runnable route = mapOfRoutes.get(keyBind);
        if (route == null) {
            Warn.debugMessageAndExit("No routes found!", -1);
        } else {
            lastRoute = currentRoute;
            currentRoute = route;
            route.run();
        }
    }
}
