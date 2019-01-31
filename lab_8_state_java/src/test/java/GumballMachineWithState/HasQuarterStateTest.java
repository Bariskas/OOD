package GumballMachineWithState;

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
    public void testDispense() throws Exception {
        GumballMachine gm = new GumballMachine(5);
        HasQuarterState hasQuarterState = new HasQuarterState(gm);
        gm.setState(hasQuarterState);
        hasQuarterState.dispense();
        assertEquals(outContent.toString(), "No gumball dispensed\n");
    }
}