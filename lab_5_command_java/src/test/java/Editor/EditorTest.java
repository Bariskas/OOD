package Editor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class EditorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testStart() {
        String data = "exit\r\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Editor editor = new Editor();
        editor.start();
        assertEquals(outContent.toString().replace("\r\n", "\n"), "Commands list:\n" +
                "  help: Help\n" +
                "  exit: exit\n" +
                "  setDocumentTitle: Changes value. Args: <new value>\n" +
                "  insertParagraph: Insert paragraph. Args: <position>|end <paragrapg text>\n" +
                "  insertImage: Insert image. Args: <position>|end <width> <height> <path>\n" +
                "  replaceText: Replace text. Args: <position> <new text>\n" +
                "  resizeImage: Resize image. Args: <position> <new width> <new height>\n" +
                "  deleteItem: Delete item. Args: <position>\n" +
                "  save: Save as Html document. Args: <path>\n" +
                "  list: Show document\n" +
                "  undo: undo command\n" +
                "  redo: redo undone command\n");
    }

    @Test
    public void testHelp() {
        String data = "help\r\nexit\r\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Editor editor = new Editor();
        editor.start();
        assertEquals(outContent.toString().replace("\r\n", "\n"), "Commands list:\n" +
                "  help: Help\n" +
                "  exit: exit\n" +
                "  setDocumentTitle: Changes value. Args: <new value>\n" +
                "  insertParagraph: Insert paragraph. Args: <position>|end <paragrapg text>\n" +
                "  insertImage: Insert image. Args: <position>|end <width> <height> <path>\n" +
                "  replaceText: Replace text. Args: <position> <new text>\n" +
                "  resizeImage: Resize image. Args: <position> <new width> <new height>\n" +
                "  deleteItem: Delete item. Args: <position>\n" +
                "  save: Save as Html document. Args: <path>\n" +
                "  list: Show document\n" +
                "  undo: undo command\n" +
                "  redo: redo undone command\n" +
                "Commands list:\n" +
                "  help: Help\n" +
                "  exit: exit\n" +
                "  setDocumentTitle: Changes value. Args: <new value>\n" +
                "  insertParagraph: Insert paragraph. Args: <position>|end <paragrapg text>\n" +
                "  insertImage: Insert image. Args: <position>|end <width> <height> <path>\n" +
                "  replaceText: Replace text. Args: <position> <new text>\n" +
                "  resizeImage: Resize image. Args: <position> <new width> <new height>\n" +
                "  deleteItem: Delete item. Args: <position>\n" +
                "  save: Save as Html document. Args: <path>\n" +
                "  list: Show document\n" +
                "  undo: undo command\n" +
                "  redo: redo undone command\n");
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }
}