package MultiGumballMachineWithState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GumballMachineTest {
    private final PrintStream originalOut = System.out;
    private final String originSeparator = System.lineSeparator();
    private ByteArrayOutputStream outContent;

    @Before
    public void setUpStreams() {
        System.setProperty("line.separator", "\n");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setProperty("line.separator", originSeparator);
        System.setOut(originalOut);
    }

    @Test
    public void testTransitionFromSoldOutState() {
        int starterGumballCount = 0;
        int newGumballCount = 3;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 0 gumballs\n" +
                "Machine is sold out\n");
        checkMachineState(gm, starterGumballCount, 0, SoldOutState.class);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 0, SoldOutState.class);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, SoldOutState.class);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount, 0, SoldOutState.class);
        gm.addQuarter();
        gm.refill(newGumballCount);
        checkMachineState(gm, newGumballCount, 1, HasQuarterState.class);
    }

    @Test
    public void testTransitionFromNoQuarterState() {
        int starterGumballCount = 5;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 5 gumballs\n" +
                "Machine is waiting for quarter\n");
        checkMachineState(gm, starterGumballCount, 0, NoQuarterState.class);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, NoQuarterState.class);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount, 0, NoQuarterState.class);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 1, HasQuarterState.class);
    }

    @Test
    public void testTransitionFromHasQuarterState() {
        int starterGumballCount = 5;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 5 gumballs\n" +
                "Machine is waiting for quarter\n");
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 1, HasQuarterState.class);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 2, HasQuarterState.class);
        gm.ejectQuarter();
        checkMachineState(gm, starterGumballCount, 0, NoQuarterState.class);
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 1, HasQuarterState.class);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount - 1, 0, NoQuarterState.class);
        gm.insertQuarter();
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount - 1, 2, HasQuarterState.class);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount - 2, 1, HasQuarterState.class);
    }

    @Test
    public void testTransitionFromHasQuarterToSoldOutState() {
        int starterGumballCount = 1;
        GumballMachine gm = new GumballMachine(starterGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 1 gumball\n" +
                "Machine is waiting for quarter\n");
        gm.insertQuarter();
        checkMachineState(gm, starterGumballCount, 1, HasQuarterState.class);
        gm.turnCrank();
        checkMachineState(gm, starterGumballCount - 1, 0, SoldOutState.class);
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
        checkMachineState(gm, starterGumballCount, 0, SoldOutState.class);
        gm.refill(3);
        checkMachineState(gm, newGumballCount, 0, NoQuarterState.class);
        gm.insertQuarter();
        checkMachineState(gm, newGumballCount, 1, HasQuarterState.class);
        gm.refill(3);
        checkMachineState(gm, newGumballCount, 1, HasQuarterState.class);
    }

    private void checkMachineState(GumballMachine gm, int gumballCount, int quarterCount, Class stateClass) {
        assertEquals(gm.getGumballCount(), gumballCount);
        assertEquals(gm.getQuarterCount(), quarterCount);
        assertEquals(gm.getState().getClass(), stateClass);
    }
}