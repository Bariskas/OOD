package Editor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class EditorMenu {
    private Editor editor;
    private boolean exit = false;

    private ArrayList<MenuItem> items;

    private class MenuItem {
        public String shortcut;
        public String description;
        public Method command;

        public MenuItem(String inputShortcut, String inputDescription, Method inputMethod) {
            shortcut = inputShortcut;
            description = inputDescription;
            command = inputMethod;
        }
    }

    public EditorMenu(Editor hostEditor) {
        editor = hostEditor;
        items = new ArrayList<>(10);
    }

    public void run() {
        showInstructions();

        Scanner scanInput = new Scanner(System.in);
        Boolean nextCommandAvailable = true;
        while (!exit && nextCommandAvailable) {
            try {
                nextCommandAvailable = executeCommand(scanInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Can't parse arguments");
            } catch (Exception e) {
                System.out.println("Error while executing command.");
            }
        }
    }

    public void addItem(String shortcut, String description, Method method) {
        items.add(new MenuItem(shortcut, description, method));
    }

    public void showInstructions() {
        System.out.println("Commands list:");
        for (MenuItem item : items) {
            System.out.printf("  %s: %s\n", item.shortcut, item.description);
        }
    }

    public void exit() {
        exit = true;
    }

    private boolean executeCommand(String commandString) {
        String commandParts[] = commandString.split(" ", 2);
        String commandName = commandParts[0];
        String commandBody = commandParts.length == 2 ? commandParts[1] : "";

        MenuItem chosenItem = findItemWithName(commandName);
        if (chosenItem != null) {
            try {
                chosenItem.command.invoke(editor, commandBody);
            } catch (Exception e) {
                System.out.println("Invalid command format. Check help.");
            }
        } else {
            System.out.println("Unknown command");
        }

        return !exit;
    }

    private MenuItem findItemWithName(String commandName) {
        for (MenuItem menuItem : items) {
            if (menuItem.shortcut.equals(commandName)) {
                return menuItem;
            }
        }
        return null;
    }
}

