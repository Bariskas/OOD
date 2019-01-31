package MultiGumballMachineWithState;

public class GumballMachine {
 	public static final int MAX_QUARTER_COUNT = 5;

	GumballMachineMenu menu;

	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;

	State state;
	int gumballCount = 0;
	int quarterCount = 0;

	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);

		this.gumballCount = numberGumballs;
 		if (numberGumballs > 0) {
			state = noQuarterState;
		} else {
			state = soldOutState;
		}

		menu = new GumballMachineMenu(this);
	}

	public void start() {
		menu.run();
	}

	public void insertQuarter() {
		state.insertQuarter();
	}

	public void ejectQuarter() {
		state.ejectQuarter();
	}

	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}

	void refill(int count) {
		state.refill(count);
	}

	void setState(State state) {
		this.state = state;
	}

	void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if (gumballCount != 0) {
			gumballCount = gumballCount - 1;
		}
	}

	int getGumballCount() {
		return gumballCount;
	}

	void setGumballCount(int count) {
		gumballCount = count;
	}

	void addQuarter() {
		quarterCount++;
	}

	void removeQuarter() {
		quarterCount--;
	}

	void resetQuarterCount() {
		quarterCount = 0;
	}

	int getQuarterCount() {
		return quarterCount;
	}

    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }
 
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004");
		result.append("\nInventory: " + gumballCount + " gumball");
		if (gumballCount != 1) {
			result.append("s");
		}
		result.append("\n");
		result.append("Machine is " + state.toString() + "\n");
		return result.toString();
	}
}
