package Editor;

public class ChangeTitleCommand extends CommonDocumentCommand {
    private EditorDocument.EditorDocumentTitle documentTitle;
    private String oldTitle;
    private String newTitle;

    public ChangeTitleCommand(EditorDocument.EditorDocumentTitle title, String newTitle) {
        this.documentTitle = title;
        this.newTitle = newTitle;
    }

    @Override
    public void execute() {
        oldTitle = this.documentTitle.value;
        documentTitle.value = newTitle;
    }

    @Override
    public void unexecute() {
        documentTitle.value = oldTitle;
    }
}
