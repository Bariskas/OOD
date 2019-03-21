package Editor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplaceTextCommandTest {
    @Test
    public void testReplaceTextCommandWork() {
        String newText = "newText";
        TextHolder textHolder = new TextHolder() {
            private String text = "";
            @Override
            public String getText() {
                return text;
            }

            @Override
            public void setText(String text) {
                this.text = text;
            }
        };

        ReplaceTextCommand command = new ReplaceTextCommand(textHolder, newText);
        assertEquals(textHolder.getText(), "");
        command.execute();
        assertEquals(textHolder.getText(), newText);
        command.unexecute();
        assertEquals(textHolder.getText(), "");

    }
}