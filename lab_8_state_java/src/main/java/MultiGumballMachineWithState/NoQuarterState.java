package MultiGumballMachineWithState;

public class NoQuarterState extends BaseState {

	public NoQuarterState(GumballMachineContext gumballMachine) {
		super(gumballMachine);
	}

	public void insertQuarter() {
		System.out.println("You inserted a quarter");
		gumballMachine.addQuarter();
		gumballMachine.setHasQuarterState();
	}
 
	public void ejectQuarter() {
		System.out.println("You haven't inserted a quarter");
	}
 
	public void turnCrank() {
		System.out.println("You turned, but there's no quarter");
	 }
 
	public void dispense() {
		System.out.println("You need to pay first");
	} 
 
	public String toString() {
		return "waiting for quarter";
	}
}
