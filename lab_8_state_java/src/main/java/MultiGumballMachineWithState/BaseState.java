package MultiGumballMachineWithState;

import GumballMachineWithState.*;

abstract class BaseState implements State {
    protected GumballMachine gumballMachine;

    public BaseState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void refill(int count) {
        if (count >= 0) {
            gumballMachine.setGumballCount(count);
            System.out.println("Machine was refilled with " + count + " gumballs");
        } else {
            System.out.println("Machine cant be refilled with " + count + " gumballs");
        }
    }
}
