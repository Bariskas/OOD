package MultiGumballMachineWithState;

import java.util.Scanner;

public class GumballMachineMenu {
    private GumballMachine gumballMachine;

    public GumballMachineMenu(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void run() {
        showInstructions();

        Scanner scanInput = new Scanner(System.in);
        Boolean nextCommandAvailable = true;
        while (nextCommandAvailable) {
            try {
                nextCommandAvailable = executeCommand(scanInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Can't parse arguments");
            } catch (Exception e) {
                System.out.println("Error while executing command.");
            }
        }
    }

    public void showInstructions() {
        System.out.println("");
        System.out.println("Commands list:");
        System.out.println("help");
        System.out.println("status");
        System.out.println("insertQuarter");
        System.out.println("ejectQuarter");
        System.out.println("turnCrank");
        System.out.println("refill <count>");
        System.out.println("exit");
    }

    private boolean executeCommand(String command) {
        String commandParts[] = command.split(" ", 2);
        String commandName = commandParts[0];

        switch (commandName.toLowerCase()) {
            case "help":
                showInstructions();
                break;
            case "status":
                System.out.println(gumballMachine.toString());
                break;
            case "insertquarter":
                gumballMachine.insertQuarter();
                break;
            case "ejectquarter":
                gumballMachine.ejectQuarter();
                break;
            case "turncrank":
                gumballMachine.turnCrank();
                break;
            case "refill":
                if (commandParts.length == 2) {
                    int count = Integer.parseInt(commandParts[1]);
                    gumballMachine.refill(count);
                } else {
                    System.out.println("Wrong refill command format");
                }
                break;
            case "exit":
                return false;
            default:
                System.out.println("Unknown command");
        }
        return true;
    }
}
