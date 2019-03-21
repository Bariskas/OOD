package Editor;

public class ResizeImageCommand extends CommonDocumentCommand {
    private Resizable resizableElement;
    private Integer oldWidth;
    private Integer oldHeight;
    private Integer newWidth;
    private Integer newHeight;

    public ResizeImageCommand(Resizable resizableElement, Integer newWidth, Integer newHeight) {
        this.resizableElement = resizableElement;
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    @Override
    public void execute() {
        oldHeight = resizableElement.getHeight();
        oldWidth = resizableElement.getWidth();
        resizableElement.setHeight(newHeight);
        resizableElement.setWidth(newWidth);
    }

    @Override
    public void unexecute() {
        resizableElement.setHeight(oldHeight);
        resizableElement.setWidth(oldWidth);
    }
}
