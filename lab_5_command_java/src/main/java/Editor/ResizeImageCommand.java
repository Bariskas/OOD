package Editor;

import java.util.ArrayList;

public class ResizeImageCommand extends CommonDocumentCommand {
    private Resizable resizebleElement;
    private Integer oldWidth;
    private Integer oldHeight;
    private Integer newWidth;
    private Integer newHeight;

    public ResizeImageCommand(Resizable resizableElement, Integer newWidth, Integer newHeight) {
        this.resizebleElement = resizableElement;
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    @Override
    public void execute() {
        oldHeight = resizebleElement.getHeight();
        oldWidth = resizebleElement.getWidth();
        resizebleElement.setHeight(newHeight);
        resizebleElement.setWidth(newWidth);
    }

    @Override
    public void unexecute() {
        resizebleElement.setHeight(oldHeight);
        resizebleElement.setWidth(oldWidth);
    }
}
