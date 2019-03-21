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
        Integer newWidth = 100;
        Integer newHeight = 100;

        Image image = items.get(0).getImage();
        checkImageSize(image, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        ResizeImageCommand command = new ResizeImageCommand(image, newWidth, newHeight);
        command.execute();
        checkImageSize(image, newWidth, newHeight);
        command.unexecute();
        checkImageSize(image, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    private void checkImageSize(Image image, Integer width, Integer height) {
        assertEquals(items.get(0).getImage().getHeight(), height);
        assertEquals(items.get(0).getImage().getWidth(), width);
    }
}