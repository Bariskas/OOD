package GumballMachineWithState;

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
    public void testSoldOutState() {
        int defaultGumballCount = 0;
        GumballMachine soldOutGm = new GumballMachine(defaultGumballCount);
        assertEquals(soldOutGm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 0 gumballs\n" +
                "Machine is sold out\n");
        checkMachineState(soldOutGm, defaultGumballCount, SoldOutState.class, "");
        soldOutGm.insertQuarter();
        checkMachineState(soldOutGm, defaultGumballCount, SoldOutState.class, "You can't insert a quarter, the machine is sold out\n");
        soldOutGm.ejectQuarter();
        checkMachineState(soldOutGm, defaultGumballCount, SoldOutState.class, "You can't eject, you haven't inserted a quarter yet\n");
        soldOutGm.turnCrank();
        checkMachineState(soldOutGm, defaultGumballCount, SoldOutState.class, "You turned, but there are no gumballs\nNo gumball dispensed\n");
    }

    @Test
    public void testInitialState() {
        int defaultGumballCount = 5;
        GumballMachine gm = new GumballMachine(defaultGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 5 gumballs\n" +
                "Machine is waiting for quarter\n");
        checkMachineState(gm, defaultGumballCount, NoQuarterState.class, "");
        gm.ejectQuarter();
        checkMachineState(gm, defaultGumballCount, NoQuarterState.class, "You haven't inserted a quarter\n");
        gm.turnCrank();
        checkMachineState(gm, defaultGumballCount, NoQuarterState.class, "You turned, but there's no quarter\nYou need to pay first\n");
        gm.insertQuarter();
        checkMachineState(gm, defaultGumballCount, HasQuarterState.class, "You inserted a quarter\n");
    }

    @Test
    public void testHasQuearterState() {
        int defaultGumballCount = 5;
        GumballMachine gm = new GumballMachine(defaultGumballCount);
        gm.insertQuarter();
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 5 gumballs\n" +
                "Machine is waiting for turn of crank\n");
        outContent.reset();
        checkMachineState(gm, defaultGumballCount, HasQuarterState.class, "");
        gm.insertQuarter();
        checkMachineState(gm, defaultGumballCount, HasQuarterState.class, "You can't insert another quarter\n");
        gm.ejectQuarter();
        checkMachineState(gm, defaultGumballCount, NoQuarterState.class, "Quarter returned\n");
        gm.insertQuarter();
        outContent.reset();
        gm.turnCrank();
        checkMachineState(gm, defaultGumballCount - 1, NoQuarterState.class, "You turned...\nA gumball comes rolling out the slot...\n");
    }

    @Test
    public void testTurnCrankWithOneGumballState() {
        int defaultGumballCount = 1;
        GumballMachine gm = new GumballMachine(defaultGumballCount);
        gm.insertQuarter();
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 1 gumball\n" +
                "Machine is waiting for turn of crank\n");
        outContent.reset();
        gm.turnCrank();
        checkMachineState(gm, defaultGumballCount - 1, SoldOutState.class, "You turned...\nA gumball comes rolling out the slot...\nOops, out of gumballs!\n");
    }

    @Test
    public void testRefillGumballState() {
        int defaultGumballCount = 0;
        int newGumballCount = 5;
        GumballMachine gm = new GumballMachine(defaultGumballCount);
        gm.insertQuarter();
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 0 gumballs\n" +
                "Machine is sold out\n");
        assertEquals(gm.getCount(), defaultGumballCount);
        assertSame(gm.getState().getClass(), SoldOutState.class);
        outContent.reset();
        gm.refill(newGumballCount);
        assertEquals(gm.toString(), "\n" +
                "Mighty Gumball, Inc.\n" +
                "Java-enabled Standing Gumball Model #2004\n" +
                "Inventory: 5 gumballs\n" +
                "Machine is waiting for quarter\n");
        assertEquals(gm.getCount(), newGumballCount);
        assertSame(gm.getState().getClass(), NoQuarterState.class);
    }

    private void checkMachineState(GumballMachine gm, int gumballCount, Class stateClass, String output) {
        assertEquals(gm.getCount(), gumballCount);
        assertEquals(gm.getState().getClass(), stateClass);
        assertEquals(outContent.toString(), output);
        outContent.reset();
    }
}