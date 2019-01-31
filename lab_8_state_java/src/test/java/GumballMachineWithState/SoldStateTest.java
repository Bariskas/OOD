package GumballMachineWithState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SoldStateTest {
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
    public void testSoldState() throws Exception {
        GumballMachine gm = new GumballMachine(5);
        SoldState soldState = new SoldState(gm);
        gm.setState(soldState);
        assertEquals(soldState.toString(), "dispensing a gumball");
        soldState.dispense();
        assertEquals(outContent.toString(), "A gumball comes rolling out the slot...\n");
        outContent.reset();
        soldState.ejectQuarter();
        assertEquals(outContent.toString(), "Sorry, you already turned the crank\n");
        outContent.reset();
        soldState.turnCrank();
        assertEquals(outContent.toString(), "Turning twice doesn't get you another gumball!\n");
        outContent.reset();
        soldState.insertQuarter();
        assertEquals(outContent.toString(), "Please wait, we're already giving you a gumball\n");
    }
}