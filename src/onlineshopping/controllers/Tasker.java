package onlineshopping.controllers;

import onlineshopping.views.Warn;

import java.util.HashMap;
import java.util.Map;

public class Tasker {
    private final String pageName;
    private final Map<Integer, Runnable> tasks = new HashMap<>();

    public Tasker(String pageName) {
        this.pageName = pageName;
    }

    public void addTask(int keyBind, Runnable task) {
        tasks.put(keyBind, task);
    }

    public void runPrompt() {
        runTask(getInput());
    }

    private int getInput() {
        if (tasks.isEmpty()) {
            Warn.debugMessageAndExit("No tasks set in " + pageName + "!", -1);
        }
        System.out.print(">> ");
        try {
            return Integer.parseInt(new java.util.Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            Warn.invalidInput();
            return getInput();
        }
    }

    private void runTask(int keyBind) {
        if (tasks.containsKey(keyBind)) {
            tasks.get(keyBind).run();
        } else {
            Warn.debugMessage("No task found in " + pageName + "!");
        }
    }

}
