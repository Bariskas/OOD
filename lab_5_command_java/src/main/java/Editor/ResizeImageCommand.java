package Editor;

import java.util.ArrayList;

public class ResizeImageCommand implements DocumentCommand {
    private ArrayList<DocumentItem> items;
    private Integer position;
    private Image targetImage;
    private Integer oldWidth;
    private Integer oldHeight;
    private Integer newWidth;
    private Integer newHeight;

    public ResizeImageCommand(ArrayList<DocumentItem> items, Integer position, Integer newWidth, Integer newHeight) {
        this.items = items;
        this.position = position;
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    @Override
    public void execute() {
        DocumentItem item;
        try {
            item = items.get(position);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }

        targetImage = item.getImage();
        if (targetImage == null) {
            throw new IllegalArgumentException("Try to resize Paragraph");
        }

        oldHeight = targetImage.getHeight();
        oldWidth = targetImage.getWidth();
        targetImage.setHeight(newHeight);
        targetImage.setWidth(newWidth);
    }

    @Override
    public void unexecute() {
        targetImage.setHeight(oldHeight);
        targetImage.setWidth(oldWidth);
    }
}
