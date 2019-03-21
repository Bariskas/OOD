package MultiGumballMachineWithState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class HasQuarterStateTest {
    private final PrintStream originalOut = System.out;
    private final String originSeparator = System.lineSeparator();
    private ByteArrayOutputStream outContent;
    private GumballMachine gumballMachine;
    private HasQuarterState hasQuarterState;

    @Before
    public void setUpStreams() {
        System.setProperty("line.separator", "\n");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gumballMachine = new GumballMachine(5);
        hasQuarterState =  new HasQuarterState(gumballMachine.contextImpl);
        gumballMachine.setState(hasQuarterState);
    }

    @After
    public void restoreStreams() {
        System.setProperty("line.separator", originSeparator);
        System.setOut(originalOut);
        gumballMachine = null;
    }

    @Test
    public void testInsertQuarter() throws Exception {
        hasQuarterState.insertQuarter();
        assertEquals(outContent.toString(), "You inserted a quarter\n");
        outContent.reset();
        hasQuarterState.insertQuarter();
        assertEquals(outContent.toString(), "You inserted a quarter\n");
        outContent.reset();
        hasQuarterState.insertQuarter();
        assertEquals(outContent.toString(), "You inserted a quarter\n");
        outContent.reset();
        hasQuarterState.insertQuarter();
        assertEquals(outContent.toString(), "You inserted a quarter\n");
        outContent.reset();
        hasQuarterState.insertQuarter();
        assertEquals(outContent.toString(), "You inserted a quarter\n");
        outContent.reset();
        hasQuarterState.insertQuarter();
        assertEquals(outContent.toString(), "You can insert only " + GumballMachine.MAX_QUARTER_COUNT + " quarters\n");
        outContent.reset();
    }

    @Test
    public void testEjectQuarter() throws Exception {
        hasQuarterState.insertQuarter();
        outContent.reset();
        hasQuarterState.ejectQuarter();
        assertEquals(outContent.toString(), "Quarter returned\n");
        hasQuarterState.insertQuarter();
        hasQuarterState.insertQuarter();
        outContent.reset();
        hasQuarterState.ejectQuarter();
        assertEquals(outContent.toString(), "Quarters returned\n");
    }

    @Test
    public void testTurnCrank() throws Exception {
        hasQuarterState.insertQuarter();
        outContent.reset();
        hasQuarterState.turnCrank();
        assertEquals(outContent.toString(), "You turned...\n");
    }

    @Test
    public void testDispense() throws Exception {
        hasQuarterState.dispense();
        assertEquals(outContent.toString(), "No gumball dispensed\n");
    }

    @Test
    public void testRefill() throws Exception {
        hasQuarterState.refill(10);
        assertEquals(outContent.toString(), "Machine was refilled with " + 10 + " gumballs\n");
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(hasQuarterState.toString(), "waiting for turn of crank");
    }
}