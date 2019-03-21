package MultiGumballMachineWithState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SoldOutStateTest {
    private final PrintStream originalOut = System.out;
    private final String originSeparator = System.lineSeparator();
    private ByteArrayOutputStream outContent;
    private GumballMachine gumballMachine;
    private SoldOutState soldOutState;

    @Before
    public void setUpStreams() {
        System.setProperty("line.separator", "\n");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gumballMachine = new GumballMachine(5);
        soldOutState = new SoldOutState(gumballMachine.contextImpl);
        gumballMachine.setState(soldOutState);
    }

    @After
    public void restoreStreams() {
        System.setProperty("line.separator", originSeparator);
        System.setOut(originalOut);
        gumballMachine = null;
        soldOutState = null;
    }

    @Test
    public void testInsertQuarter() throws Exception {
        soldOutState.insertQuarter();
        assertEquals(outContent.toString(), "You can't insert a quarter, the machine is sold out\n");
    }

    @Test
    public void testEjectQuarter() throws Exception {
        gumballMachine.addQuarter();
        soldOutState.ejectQuarter();
        assertEquals(outContent.toString(), "Quarter returned\n");
        outContent.reset();
        gumballMachine.addQuarter();
        gumballMachine.addQuarter();
        soldOutState.ejectQuarter();
        assertEquals(outContent.toString(), "Quarters returned\n");
        outContent.reset();
        soldOutState.ejectQuarter();
        assertEquals(outContent.toString(), "You can't eject, you haven't inserted a quarter yet\n");
    }

    @Test
    public void testTurnCrank() throws Exception {
        soldOutState.turnCrank();
        assertEquals(outContent.toString(), "You turned, but there are no gumballs\n");
    }

    @Test
    public void testDispense() throws Exception {
        soldOutState.dispense();
        assertEquals(outContent.toString(), "No gumball dispensed\n");
    }

    @Test
    public void testRefill() throws Exception {
        soldOutState.refill(10);
        assertEquals(outContent.toString(), "Machine was refilled with " + 10 + " gumballs\n");
        outContent.reset();
        soldOutState.refill(-1);
        assertEquals(outContent.toString(), "Machine cant be refilled with " + -1 + " gumballs\n");
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(soldOutState.toString(), "sold out");
    }
}