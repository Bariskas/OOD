package MultiGumballMachineWithState;

public class HasQuarterState extends BaseState {

	public HasQuarterState(GumballMachineContext gumballMachine) {
		super(gumballMachine);
	}

	public void insertQuarter() {
		if (gumballMachine.getQuarterCount() >= GumballMachine.MAX_QUARTER_COUNT) {
			System.out.println("You can insert only " + GumballMachine.MAX_QUARTER_COUNT + " quarters");
		} else {
			gumballMachine.addQuarter();
			System.out.println("You inserted a quarter");
		}
	}
 
	public void ejectQuarter() {
		if (gumballMachine.getQuarterCount() > 1) {
			System.out.println("Quarters returned");
		} else {
			System.out.println("Quarter returned");
		}
		gumballMachine.resetQuarterCount();
		gumballMachine.setNoQuarterState();
	}
 
	public void turnCrank() {
		System.out.println("You turned...");
		gumballMachine.removeQuarter();
		gumballMachine.setSoldState();
	}

    public void dispense() {
        System.out.println("No gumball dispensed");
    }

	public String toString() {
		return "waiting for turn of crank";
	}
}
