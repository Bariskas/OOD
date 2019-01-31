package Editor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditorDocumentTest {
    private EditorDocument document;

    @Before
    public void setUpStreams() {
        document = new EditorDocument();
    }

    @After
    public void restoreStreams() {
        document = null;
    }

    @Test
    public void testSetTitle() {
        assertEquals(document.getDocumentTitle(), "");
        document.setDocumentTitle("TestTitle");
        assertEquals(document.getDocumentTitle(), "TestTitle");
    }

    @Test
    public void testGetItemsList() {
        assertEquals(document.getItemsList(), "");
        document.insertParagraph(new Paragraph("Dummy1"));
        assertEquals(document.getItemsList(), "1. Paragraph: Dummy1\n");
        document.insertParagraph(new Paragraph("Dummy2"));
        assertEquals(
                document.getItemsList().replace("\r\n", "\n"),
                "1. Paragraph: Dummy1\n2. Paragraph: Dummy2\n"
        );
    }

    @Test
    public void testGetHtml() {
        assertEquals(document.getHtml(), "<html><head><title></title></head><body></body></html>");
        document.insertParagraph(new Paragraph("Dummy1"));
        assertEquals(document.getHtml(), "<html><head><title></title></head><body><p>Dummy1</p></body></html>");
    }
}