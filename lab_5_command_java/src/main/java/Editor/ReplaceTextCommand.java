package Editor;

import java.util.ArrayList;

public class ReplaceTextCommand implements DocumentCommand {
    private ArrayList<DocumentItem> items;
    private Integer position;
    private Paragraph targetParagraph;
    private String oldText;
    private String newText;

    public ReplaceTextCommand(ArrayList<DocumentItem> items, Integer position, String newText) {
        this.items = items;
        this.position = position;
        this.newText = newText;
    }

    @Override
    public void execute() {
        DocumentItem item;
        try {
            item = items.get(position);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }

        targetParagraph = item.getParagraph();
        if (targetParagraph == null) {
            throw new IllegalArgumentException("Try to replace text for image content");
        }

        oldText = targetParagraph.getText();
        targetParagraph.setText(newText);
    }

    @Override
    public void unexecute() {
        targetParagraph.setText(oldText);
    }
}
