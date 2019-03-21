package MultiGumballMachineWithState;

public class GumballMachine  {
    class GumballMachineContextImpl implements GumballMachineContext {
        public void setSoldOutState() {
            GumballMachine.this.setSoldOutState();
        }

        public void setNoQuarterState() {
            GumballMachine.this.setNoQuarterState();
        }

        public void setHasQuarterState() {
            GumballMachine.this.setHasQuarterState();
        }

        public void setSoldState() {
            GumballMachine.this.setSoldState();
        }

        public int getGumballCount() {
            return GumballMachine.this.getGumballCount();
        }

        public int getQuarterCount() {
            return GumballMachine.this.getQuarterCount();
        }

        public void setGumballCount(int count) {
            GumballMachine.this.setGumballCount(count);
        }

        public void addQuarter() {
            GumballMachine.this.addQuarter();
        }

        public void removeQuarter() {
            GumballMachine.this.removeQuarter();
        }

        public void resetQuarterCount() {
            GumballMachine.this.resetQuarterCount();
        }

        public void releaseBall() {
            GumballMachine.this.releaseBall();
        }
    }

    public static final int MAX_QUARTER_COUNT = 5;

    GumballMachineContextImpl contextImpl;
    GumballMachineMenu menu;

    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;

    State state;
    private int gumballCount = 0;
    private int quarterCount = 0;

    public GumballMachine(int numberGumballs) {
        contextImpl = new GumballMachineContextImpl();
        soldOutState = new SoldOutState(contextImpl);
        noQuarterState = new NoQuarterState(contextImpl);
        hasQuarterState = new HasQuarterState(contextImpl);
        soldState = new SoldState(contextImpl);

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

    public void refill(int count) {
        state.refill(count);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (gumballCount != 0) {
            gumballCount = gumballCount - 1;
        }
    }

    public int getGumballCount() {
        return gumballCount;
    }

    public void setGumballCount(int count) {
        gumballCount = count;
    }

    public void addQuarter() {
        quarterCount++;
    }

    public void removeQuarter() {
        quarterCount--;
    }

    public void resetQuarterCount() {
        quarterCount = 0;
    }

    public int getQuarterCount() {
        return quarterCount;
    }

    public State getState() {
        return state;
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

    private void setSoldOutState() {
        state = soldOutState;
    }

    private void setNoQuarterState() {
        state = noQuarterState;
    }

    private void setHasQuarterState() {
        state = hasQuarterState;
    }

    private void setSoldState() {
        state = soldState;
    }
}
