package Editor;

import java.util.ArrayList;

public class InsertDocumentItemCommand extends CommonDocumentCommand {
    private final ArrayList<DocumentItem> items;
    private final DocumentItem item;
    private int insertPos;

    public InsertDocumentItemCommand(ArrayList<DocumentItem> items, DocumentItem item) {
        this.items = items;
        this.item = item;
        insertPos = -1;
    }

    public InsertDocumentItemCommand(ArrayList<DocumentItem> items, DocumentItem item, int insertPos) {
        this.items = items;
        this.item = item;
        this.insertPos = insertPos;
    }

    @Override
    public void execute() {
        if (insertPos == -1 || insertPos == items.size()) {
            items.add(item);
        } else {
            items.add(insertPos, item);
        }
    }

    @Override
    public void unexecute() {
        if (insertPos == -1 || insertPos == items.size()) {
            items.remove(items.size() - 1);
        } else {
            items.remove(insertPos);
        }
    }
}
