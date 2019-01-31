package Editor;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private class HistoryItem {
        public HistoryItem(DocumentCommand command) {
            this.isInvoked = true;
            this.command = command;
        }

        public boolean isInvoked;
        public DocumentCommand command;
    }

    private static final Integer MAX_ITEMS_COUNT = 10;

    private ArrayList<HistoryItem> historyItems;

    public CommandHistory() {
        historyItems = new ArrayList<>(MAX_ITEMS_COUNT);
    }

    public void add(DocumentCommand command) {
        deleteUninvoked();
        if (historyItems.size() == MAX_ITEMS_COUNT) {
            historyItems.remove(0);
        }
        historyItems.add(new HistoryItem(command));
    }

    public void undo() {
        HistoryItem lastInvoked = findLastInvoked();

        if (lastInvoked != null) {
            lastInvoked.command.unexecute();
            lastInvoked.isInvoked = false;
        }
    }

    public void redo() {
        HistoryItem firstUninvoked = findFirstUninvoked();

        if (firstUninvoked != null) {
            firstUninvoked.command.execute();
            firstUninvoked.isInvoked = true;
        }
    }

    public boolean canUndo() {
        return (findLastInvoked() != null);
    }

    public boolean canRedo() {
        return (findFirstUninvoked() != null);
    }

    private HistoryItem findLastInvoked() {
        HistoryItem lastInvokedAction = null;
        for (HistoryItem historyItem : historyItems) {
            if (historyItem.isInvoked) {
                lastInvokedAction = historyItem;
            }
        }

        return lastInvokedAction;
    }

    private HistoryItem findFirstUninvoked() {
        for (HistoryItem historyItem : historyItems) {
            if (!historyItem.isInvoked) {
                return historyItem;
            }
        }

        return null;
    }

    private void deleteUninvoked() {
        HistoryItem firstUninvoked = findFirstUninvoked();
        if (firstUninvoked != null) {
            Integer firstUninvokedIndex = historyItems.indexOf(firstUninvoked);
            List<HistoryItem> uninvokedItems = historyItems.subList(firstUninvokedIndex, historyItems.size());
            for (HistoryItem item : uninvokedItems) {
                item.command.destroy();
            }
            uninvokedItems.clear();
        }
    }
}
