package MultiGumballMachineWithState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SoldStateTest {
    private final PrintStream originalOut = System.out;
    private final String originSeparator = System.lineSeparator();
    private ByteArrayOutputStream outContent;
    private GumballMachine gumballMachine;
    private SoldState soldState;

    @Before
    public void setUpStreams() {
        System.setProperty("line.separator", "\n");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gumballMachine = new GumballMachine(5);
        soldState = new SoldState(gumballMachine);
        gumballMachine.setState(soldState);
    }

    @After
    public void restoreStreams() {
        System.setProperty("line.separator", originSeparator);
        System.setOut(originalOut);
        gumballMachine = null;
        soldState = null;
    }

    @Test
    public void testInsertQuarter() throws Exception {
        soldState.insertQuarter();
        assertEquals(outContent.toString(), "Please wait, we're already giving you a gumball\n");
    }

    @Test
    public void testEjectQuarter() throws Exception {
        soldState.ejectQuarter();
        assertEquals(outContent.toString(), "Sorry, you already turned the crank\n");
    }

    @Test
    public void testTurnCrank() throws Exception {
        soldState.turnCrank();
        assertEquals(outContent.toString(), "Turning twice doesn't get you another gumball!\n");
    }

    @Test
    public void testDispense() throws Exception {
        soldState.dispense();
        assertEquals(outContent.toString(), "A gumball comes rolling out the slot...\n");
    }

    @Test
    public void testDispenseWithEmptyMachine() throws Exception {
        gumballMachine.setGumballCount(0);
        soldState.dispense();
        assertEquals(outContent.toString(), "A gumball comes rolling out the slot...\nOops, out of gumballs!\n");
    }

    @Test
    public void testRefill() throws Exception {
        soldState.refill(10);
        assertEquals(outContent.toString(), "Machine cant be refilled during sold state\n");
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(soldState.toString(), "dispensing a gumball");
    }
}