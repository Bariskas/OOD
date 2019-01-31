package Editor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InsertImageCommandTest {
    private ArrayList<DocumentItem> items;

    @Before
    public void setUp() {
        items = new ArrayList<>(10);
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
    public void testInsertImage() {
        Image realImage = new Image("src/test/resources/2.jpg", 50, 50);
        InsertImageCommand command = new InsertImageCommand(items, realImage, -1);
        command.execute();
        assertEquals(items.size(), 1);
        assertEquals(items.get(0).getImage(), realImage);
        assertEquals(new File("images/image_1.jpg").exists(), true);
    }

    @Test
    public void testInsertTwoImage() {
        Image realImage = new Image("src/test/resources/2.jpg", 50, 50);
        InsertImageCommand command = new InsertImageCommand(items, realImage, -1);
        Image realImage2 = new Image("src/test/resources/winter.jpg", 50, 50);
        InsertImageCommand command2 = new InsertImageCommand(items, realImage2, -1);
        command.execute();
        command2.execute();
        assertEquals(items.size(), 2);
        assertEquals(items.get(0).getImage(), realImage);
        assertEquals(items.get(1).getImage(), realImage2);
        assertEquals(new File("images/image_1.jpg").exists(), true);
        assertEquals(new File("images/image_2.jpg").exists(), true);
    }

    @Test
    public void testCancelInsertImage() {
        Image realImage = new Image("src/test/resources/2.jpg", 50, 50);
        InsertImageCommand command = new InsertImageCommand(items, realImage, -1);
        command.execute();
        command.unexecute();
        assertEquals(items.size(), 0);
        assertEquals(new File("images/image_1.jpg").exists(), true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInsertInvalidImage() {
        Image unrealImage = new Image("src/test/resources/asfasfsa.jpg", 50, 50);
        InsertImageCommand command = new InsertImageCommand(items, unrealImage, -1);
        command.execute();
    }
}