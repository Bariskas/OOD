package MultiGumballMachineWithState;

public class SoldState extends BaseState {

	public SoldState(GumballMachine gumballMachine) {
		super(gumballMachine);
	}

	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a gumball");
	}
 
	public void ejectQuarter() {
		System.out.println("Sorry, you already turned the crank");
	}
 
	public void turnCrank() {
		System.out.println("Turning twice doesn't get you another gumball!");
	}
 
	public void dispense() {
		gumballMachine.releaseBall();
		if (gumballMachine.getGumballCount() > 0) {
			if (gumballMachine.getQuarterCount() > 0) {
				gumballMachine.setState(gumballMachine.getHasQuarterState());
			}
			else {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			}
		} else {
			System.out.println("Oops, out of gumballs!");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}

	@Override
	public void refill(int count) {
		System.out.println("Machine cant be refilled during sold state");
	}

	public String toString() {
		return "dispensing a gumball";
	}
}


