package MultiGumballMachineWithState;

public class SoldOutState extends BaseState {

	public SoldOutState(GumballMachineContext gumballMachine) {
		super(gumballMachine);
	}

	public void insertQuarter() {
		System.out.println("You can't insert a quarter, the machine is sold out");
	}
 
	public void ejectQuarter() {
		if (gumballMachine.getQuarterCount() > 1) {
			gumballMachine.resetQuarterCount();
			System.out.println("Quarters returned");
		} else if (gumballMachine.getQuarterCount() == 1){
			gumballMachine.resetQuarterCount();
			System.out.println("Quarter returned");
		} else {
			System.out.println("You can't eject, you haven't inserted a quarter yet");
		}
	}

	@Override
	public void refill(int count) {
		super.refill(count);
		if (count > 0) {
			if (gumballMachine.getQuarterCount() > 0) {
				gumballMachine.setHasQuarterState();
			} else {
				gumballMachine.setNoQuarterState();
			}
		}
	}

	public void turnCrank() {
		System.out.println("You turned, but there are no gumballs");
	}
 
	public void dispense() {
		System.out.println("No gumball dispensed");
	}
 
	public String toString() {
		return "sold out";
	}
}
