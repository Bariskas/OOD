package MultiGumballMachine;

import org.junit.Test;

import static org.junit.Assert.*;

public class GumballMachineTest {

    @Test
    public void testSoldOutState() {
        int starterGumballCount = 0;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 0 gumballs\n" +
                "Machine is sold out\n");
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
        gm.quarterCount = 1;
        checkMachineState(gm, starterGumballCount, 1, GumballMachine.SOLD_OUT);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
        gm.quarterCount = 2;
        checkMachineState(gm, starterGumballCount, 2, GumballMachine.SOLD_OUT);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
        gm.dispense();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
    }

    @Test
    public void testNoQuarterState() {
        int starterGumballCount = 5;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 5 gumballs\n" +
                "Machine is waiting for quarter\n");
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.NO_QUARTER);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.NO_QUARTER);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.NO_QUARTER);
        gm.dispense();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.NO_QUARTER);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 1, GumballMachine.HAS_QUARTER);
    }

    @Test
    public void testHasQuarterState() {
        int starterGumballCount = 5;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        gm.insertQuarter();
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 5 gumballs\n" +
                "Machine is waiting for turn of crank\n");
        checkMachineState(gm, starterGumballCount, 1, GumballMachine.HAS_QUARTER);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.NO_QUARTER);
        gm.insertQuarter();
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 2, GumballMachine.HAS_QUARTER);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.NO_QUARTER);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 1, GumballMachine.HAS_QUARTER);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount - 1, 0, GumballMachine.NO_QUARTER);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount - 1, 1, GumballMachine.HAS_QUARTER);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount - 1, 2, GumballMachine.HAS_QUARTER);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount - 2, 1, GumballMachine.HAS_QUARTER);
        gm.insertQuarter();
        gm.insertQuarter();
        gm.insertQuarter();
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount - 2, 5, GumballMachine.HAS_QUARTER);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount - 2, 5, GumballMachine.HAS_QUARTER);
        gm.dispense();
        checkMachineState(gm, starterGumballCount - 2, 5, GumballMachine.HAS_QUARTER);
    }

    @Test
    public void testSoldStat() {
        int starterGumballCount = 3;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        gm.state = GumballMachine.SOLD;
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 3 gumballs\n" +
                "Machine is delivering a gumball\n");
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD);
        gm.dispense();
        checkMachineState(gm, starterGumballCount - 1, 0, GumballMachine.NO_QUARTER);

        GumballMachine gmWithQuarter = new GumballMachine(starterGumballCount);
        gmWithQuarter.state = GumballMachine.SOLD;
        gmWithQuarter.quarterCount = 2;
        gmWithQuarter.dispense();
        checkMachineState(gmWithQuarter, starterGumballCount - 1, 2, GumballMachine.HAS_QUARTER);

        GumballMachine gmWithOneGumball = new GumballMachine(1);
        gmWithOneGumball.state = GumballMachine.SOLD;
        gmWithOneGumball.quarterCount = 2;
        gmWithOneGumball.dispense();
        checkMachineState(gmWithOneGumball, 0, 2, GumballMachine.SOLD_OUT);
    }

    @Test
    public void testRefill() {
        int starterGumballCount = 0;
        int newGumballCount = 3;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 0 gumballs\n" +
                "Machine is sold out\n");
        checkMachineState(gm, starterGumballCount, 0, GumballMachine.SOLD_OUT);
        gm.refill(3);
        checkMachineState(gm, newGumballCount, 0, GumballMachine.NO_QUARTER);
    }

    private void checkMachineState(GumballMachine gm, int gumballCount, int quarterCount, int state) {
        assertEquals(gm.getQuarterCount(), quarterCount);
        assertEquals(gm.gumballCount, gumballCount);
        assertEquals(gm.state, state);
    }
}