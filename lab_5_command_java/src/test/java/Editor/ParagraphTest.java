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

    @Test
    public void testGetText() throws Exception {
        Paragraph emptyParagraph = new Paragraph();
        assertEquals(emptyParagraph.getText(), "");
        String dummyText = "heh";
        Paragraph paragraph = new Paragraph(dummyText);
        assertEquals(paragraph.getText(), dummyText);
    }

    @Test
    public void testSetText() throws Exception {
        String dummyText = "heh";
        Paragraph paragraph = new Paragraph();
        assertEquals(paragraph.getText(), "");
        paragraph.setText(dummyText);
        assertEquals(paragraph.getText(), dummyText);
    }
}