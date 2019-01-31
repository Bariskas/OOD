package MultiGumballMachine;

public class GumballMachine {

    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;

    int state = SOLD_OUT;
    int gumballCount = 0;
    int quarterCount = 0;

    public GumballMachine(int count) {
        this.gumballCount = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    public void insertQuarter() {
        if (state == HAS_QUARTER) {
            if (getQuarterCount() >= MultiGumballMachineWithState.GumballMachine.MAX_QUARTER_COUNT) {
                System.out.println("You can insert only " + MultiGumballMachineWithState.GumballMachine.MAX_QUARTER_COUNT + " quarters");
            } else {
                addQuarter();
                System.out.println("You inserted a quarter");
            }
        } else if (state == NO_QUARTER) {
            addQuarter();
            state = HAS_QUARTER;
            System.out.println("You inserted a quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You can't insert a quarter, the machine is sold out");
        } else if (state == SOLD) {
            System.out.println("Please wait, we're already giving you a gumball");
        }
    }

    public void ejectQuarter() {
        if (state == HAS_QUARTER) {
            if (getQuarterCount() > 1) {
                System.out.println("Quarters returned");
            } else {
                System.out.println("Quarter returned");
            }
            resetQuarterCount();
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("You haven't inserted a quarter");
        } else if (state == SOLD) {
            System.out.println("Sorry, you already turned the crank");
        } else if (state == SOLD_OUT) {
            if (getQuarterCount() > 1) {
                resetQuarterCount();
                System.out.println("Quarters returned");
            } else if (getQuarterCount() == 1) {
                resetQuarterCount();
                System.out.println("Quarter returned");
            } else {
                System.out.println("You can't eject, you haven't inserted a quarter yet");
            }
        }
    }

    public void turnCrank() {
        if (state == SOLD) {
            System.out.println("Turning twice doesn't get you another gumball!");
        } else if (state == NO_QUARTER) {
            System.out.println("You turned but there's no quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You turned, but there are no gumballs");
        } else if (state == HAS_QUARTER) {
            System.out.println("You turned...");
            removeQuarter();
            state = SOLD;
            dispense();
        }
    }

    public void dispense() {
        if (state == SOLD) {
            System.out.println("A gumball comes rolling out the slot");
            if (gumballCount != 0) {
                gumballCount = gumballCount - 1;
            }
            if (gumballCount == 0) {
                System.out.println("Oops, out of gumballs!");
                state = SOLD_OUT;
            } else {
                if (getQuarterCount() > 0) {
                    state = HAS_QUARTER;
                } else {
                    state = NO_QUARTER;
                }
            }
        } else if (state == NO_QUARTER) {
            System.out.println("You need to pay first");
        } else if (state == SOLD_OUT) {
            System.out.println("No gumball dispensed");
        } else if (state == HAS_QUARTER) {
            System.out.println("No gumball dispensed");
        }
    }

    public void refill(int numGumBalls) {
        this.gumballCount = numGumBalls;
        state = NO_QUARTER;
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

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava-enabled Standing Gumball Model #2004\n");
        result.append("Inventory: " + gumballCount + " gumball");
        if (gumballCount != 1) {
            result.append("s");
        }
        result.append("\nMachine is ");
        if (state == SOLD_OUT) {
            result.append("sold out");
        } else if (state == NO_QUARTER) {
            result.append("waiting for quarter");
        } else if (state == HAS_QUARTER) {
            result.append("waiting for turn of crank");
        } else if (state == SOLD) {
            result.append("`");
        }
        result.append("\n");
        return result.toString();
    }


}


