package Editor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImageTest {

    @Test
    public void testToHtmlString() throws Exception {
        Image emptyImage = new Image();
        assertEquals(emptyImage.toHtmlString(), "<img src=\"\" width=\"0\" height=\"0\">");
        Image image = new Image("image.jpg", 50, 50);
        assertEquals(image.toHtmlString(), "<img src=\"image.jpg\" width=\"50\" height=\"50\">");
    }

    @Test
    public void testToString() throws Exception {
        Image emptyImage = new Image();
        assertEquals(emptyImage.toString(), "Image: 0 0 ");
        Image image = new Image("image.jpg", 50, 50);
        assertEquals(image.toString(), "Image: 50 50 image.jpg");
    }

    @Test
    public void testResize() throws Exception {
        Image image = new Image("image.jpg", 50, 50);
        assertEquals(image.toHtmlString(), "<img src=\"image.jpg\" width=\"50\" height=\"50\">");
        image.resize(100, 100);
        assertEquals(image.toHtmlString(), "<img src=\"image.jpg\" width=\"100\" height=\"100\">");
    }
}