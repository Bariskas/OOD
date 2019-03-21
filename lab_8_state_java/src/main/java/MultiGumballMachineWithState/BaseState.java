package MultiGumballMachineWithState;

abstract class BaseState implements State {
    protected GumballMachineContext gumballMachine;

    public BaseState(GumballMachineContext gumballMachine) {
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
