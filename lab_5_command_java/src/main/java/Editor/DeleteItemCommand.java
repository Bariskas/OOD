package Editor;

import java.util.ArrayList;

public class DeleteItemCommand extends CommonDocumentCommand {
    private ArrayList<DocumentItem> items;
    private DocumentItem deletedItem;
    private Integer position;

    public DeleteItemCommand(ArrayList<DocumentItem> items, Integer position) {
        this.items = items;
        this.position = position;
    }

    @Override
    public void execute() {
        try {
            deletedItem = items.get(position);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        items.remove(position.intValue());
    }

    @Override
    public void unexecute() {
        items.add(position, deletedItem);
    }
}
