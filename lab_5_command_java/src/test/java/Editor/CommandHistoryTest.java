package Editor;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandHistoryTest {
    @Test
    public void testAddEmptyHistory() {
        CommandHistory history = new CommandHistory();
        assertEquals(history.canRedo(), false);
        assertEquals(history.canUndo(), false);
    }

    @Test
    public void testAddOneCommand() {
        CommandHistory history = new CommandHistory();
        history.add(createDocumentCommand());
        assertEquals(history.canRedo(), false);
        assertEquals(history.canUndo(), true);
    }

    @Test
    public void testUndoWithOneCommand() {
        CommandHistory history = new CommandHistory();
        history.add(createDocumentCommand());
        history.undo();
        assertEquals(history.canRedo(), true);
        assertEquals(history.canUndo(), false);
        history.undo();
        assertEquals(history.canRedo(), true);
        assertEquals(history.canUndo(), false);
    }

    @Test
    public void testUndoWithTwoCommands() {
        CommandHistory history = new CommandHistory();
        history.add(createDocumentCommand());
        history.add(createDocumentCommand());
        history.undo();
        assertEquals(history.canRedo(), true);
        assertEquals(history.canUndo(), true);
        history.undo();
        assertEquals(history.canRedo(), true);
        assertEquals(history.canUndo(), false);
    }

    private DocumentCommand createDocumentCommand() {
        DocumentCommand dummyCommand = new DocumentCommand() {
            @Override
            public void execute() {

            }

            @Override
            public void unexecute() {

            }
        };
        return dummyCommand;
    }
}