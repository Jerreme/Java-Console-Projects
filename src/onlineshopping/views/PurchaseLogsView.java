package onlineshopping.views;

import onlineshopping.interfaces.Messenger;
import onlineshopping.models.PurchasedLog;

public class PurchaseLogsView extends Messenger {
    public void showPurchasedLogs(PurchasedLog[] logs) {
        printHeader("Purchased Logs");
        String tempDate = "";
        for (PurchasedLog log : logs) {
            final String date = log.date().toString();
            if (!tempDate.equals(date)) {
                println(date);
                tempDate = date;
            }
            final String format = String.format("· %s\t%d", log.product().getProductName(), log.product().getPrice());
            println(format);
        }

    }

    public void showNoPurchasesYet() {
        systemMessage("No purchases yet.");
    }

    public void promptAnyInput() {
        systemMessage("Press enter to continue.");
    }
}
