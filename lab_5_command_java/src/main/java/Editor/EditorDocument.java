package Editor;

import java.util.ArrayList;

public class EditorDocument {
    public class EditorDocumentTitle implements TextHolder {
        public EditorDocumentTitle() {
            value = "";
        }

        public String value;

        @Override
        public String getText() {
            return value;
        }

        @Override
        public void setText(String text) {
            value = text;
        }
    }

    private EditorDocumentTitle documentTitle;
    private ArrayList<DocumentItem> items;
    private CommandHistory history;
    private String html;

    public EditorDocument() {
        documentTitle = new EditorDocumentTitle();
        items = new ArrayList<DocumentItem>();
        history = new CommandHistory();
    }

    public int getItemsCount() {
        return items.size();
    }

    public DocumentItem getItem(int index) {
        return items.get(index);
    }

    public String getDocumentTitle() {
        return documentTitle.value;
    }

    public void setDocumentTitle(String newTitle) {
        DocumentCommand command = new ReplaceTextCommand(documentTitle, newTitle);
        addAndExecuteCommand(command);
    }

    public String getItemsList() {
        StringBuilder builder = new StringBuilder();
        Integer counter = 0;
        for (DocumentItem item : items) {
            counter++;
            builder.append(counter);
            builder.append(". ");
            builder.append(item.toString());
            builder.append('\n');
        }
        return builder.toString();
    }

    public void insertParagraph(Paragraph paragraph) {
        DocumentCommand command = new InsertDocumentItemCommand(items, new DocumentItem(paragraph));
        addAndExecuteCommand(command);
    }

    public void insertParagraph(Paragraph paragraph, Integer position) {
        DocumentCommand command = new InsertDocumentItemCommand(items, new DocumentItem(paragraph), position);
        addAndExecuteCommand(command);
    }

    public void insertImage(Image image, Integer position) {
        DocumentCommand command = new InsertImageCommand(items, image, position);
        addAndExecuteCommand(command);
    }

    public void deleteItem(Integer position) {
        DocumentCommand command = new DeleteItemCommand(items, position);
        addAndExecuteCommand(command);
    }

    public void addAndExecuteCommand(DocumentCommand command) {
        try {
            command.execute();
            history.add(command);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void undo() {
        if (history.canUndo()) {
            history.undo();
        } else {
            System.out.println("Cant do undo");
        }
    }

    public void redo() {
        if (history.canRedo()) {
            history.redo();
        } else {
            System.out.println("Cant do redo");
        }
    }

    public void replaceText(String newText, Integer position) {
        Paragraph paragraph = getItem(position).getParagraph();
        if (paragraph == null) {
            throw new IllegalArgumentException("Try to replace text for non paragraph item");
        }
        DocumentCommand command = new ReplaceTextCommand(paragraph, newText);
        addAndExecuteCommand(command);
    }

    public void resizeImage(Integer position, Integer newWidth, Integer newHeight) {
        Image image = getItem(position).getImage();
        if (image == null) {
            throw new IllegalArgumentException("Try resize non image item");
        }
        DocumentCommand command = new ResizeImageCommand(image, newWidth, newHeight);
        addAndExecuteCommand(command);
    }

    public String getHtml() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head><title>");
        builder.append(EscapeUtils.escapeHtml(documentTitle.value));
        builder.append("</title></head><body>");
        for (DocumentItem item : items) {
            builder.append(item.toHtmlString());
        }
        builder.append("</body></html>");
        return builder.toString();
    }
}
