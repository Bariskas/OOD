package Editor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ResizeImageCommandTest {
    private static final Integer DEFAULT_WIDTH = 100;
    private static final Integer DEFAULT_HEIGHT = 100;
    private ArrayList<DocumentItem> items;

    @Before
    public void setUp() {
        items = new ArrayList<>(10);

        Image realImage = new Image("src/test/resources/2.jpg", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        InsertImageCommand command = new InsertImageCommand(items, realImage, -1);
        command.execute();
        Paragraph paragraph = new Paragraph("dummy text");
        InsertDocumentItemCommand insertParagraph = new InsertDocumentItemCommand(items, new DocumentItem(paragraph), -1);
        insertParagraph.execute();
    }

    @After
    public void tearDown() {
        File imageDir = new File(Editor.IMAGES_DIR_PATH);
        if (imageDir.exists()) {
            for (File image : imageDir.listFiles()) {
                image.delete();
            }
            imageDir.delete();
        }

        items = null;
    }

    @Test
    public void testResizeImage() {
        assertEquals(items.get(0).getImage().getHeight(), DEFAULT_HEIGHT);
        assertEquals(items.get(0).getImage().getWidth(), DEFAULT_WIDTH);
        ResizeImageCommand command = new ResizeImageCommand(items, 0, 100, 100);
        command.execute();
        assertEquals(items.get(0).getImage().getHeight().intValue(), 100);
        assertEquals(items.get(0).getImage().getWidth().intValue(), 100);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTryResizeParagraph() {
        ResizeImageCommand command = new ResizeImageCommand(items, 1, 100, 100);
        command.execute();
    }
}