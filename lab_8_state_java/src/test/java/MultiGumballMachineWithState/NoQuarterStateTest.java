package MultiGumballMachineWithState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class NoQuarterStateTest {
    private final PrintStream originalOut = System.out;
    private final String originSeparator = System.lineSeparator();
    private ByteArrayOutputStream outContent;
    private GumballMachine gumballMachine;
    private NoQuarterState noQuarterState;

    @Before
    public void setUpStreams() {
        System.setProperty("line.separator", "\n");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gumballMachine = new GumballMachine(5);
        noQuarterState = new NoQuarterState(gumballMachine);
        gumballMachine.setState(noQuarterState);
    }

    @After
    public void restoreStreams() {
        System.setProperty("line.separator", originSeparator);
        System.setOut(originalOut);
        gumballMachine = null;
        noQuarterState = null;
    }

    @Test
    public void testInsertQuarter() throws Exception {
        noQuarterState.insertQuarter();
        assertEquals(outContent.toString(), "You inserted a quarter\n");
    }

    @Test
    public void testEjectQuarter() throws Exception {
        noQuarterState.ejectQuarter();
        assertEquals(outContent.toString(), "You haven't inserted a quarter\n");
    }

    @Test
    public void testTurnCrank() throws Exception {
        noQuarterState.turnCrank();
        assertEquals(outContent.toString(), "You turned, but there's no quarter\n");
    }

    @Test
    public void testDispense() throws Exception {
        noQuarterState.dispense();
        assertEquals(outContent.toString(), "You need to pay first\n");
    }

    @Test
    public void testRefill() throws Exception {
        noQuarterState.refill(10);
        assertEquals(outContent.toString(), "Machine was refilled with " + 10 + " gumballs\n");
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(noQuarterState.toString(), "waiting for quarter");
    }
}