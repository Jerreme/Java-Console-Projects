package onlineshopping.controllers;

import onlineshopping.models.PurchasedLog;
import onlineshopping.views.PurchaseLogsView;

public class PurchaseLogsController {
    final PurchaseLogsView view;

    public PurchaseLogsController(PurchaseLogsView view) {
        this.view = view;
    }

    public void showPurchasedLogs() {
        final PurchasedLog[] logs = {};
        // TODO get purchased logs from database
        if (logs.length == 0) {
            view.showNoPurchasesYet();
        } else {
            view.showPurchasedLogs(logs);
            waitForInput();
        }
        Navigator.gotoLastRoute();
    }

    private void waitForInput() {
        view.promptAnyInput();
        new java.util.Scanner(System.in).nextLine();
    }
}
