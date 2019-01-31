package Editor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParagraphTest {
    @Test
    public void testToHtmlString() throws Exception {
        Paragraph emptyParagraph = new Paragraph();
        assertEquals(emptyParagraph.toHtmlString(), "<p></p>");
        Paragraph paragraph = new Paragraph("dummy text");
        assertEquals(paragraph.toHtmlString(), "<p>dummy text</p>");
    }

    @Test
    public void testToString() throws Exception {
        Paragraph emptyParagraph = new Paragraph();
        assertEquals(emptyParagraph.toString(), "Paragraph: ");
        Paragraph paragraph = new Paragraph("dummy text");
        assertEquals(paragraph.toString(), "Paragraph: dummy text");
    }
}