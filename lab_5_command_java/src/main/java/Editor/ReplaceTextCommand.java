package Editor;

public class ReplaceTextCommand extends CommonDocumentCommand {
    private TextHolder paragraph;
    private String oldText;
    private String newText;

    public ReplaceTextCommand(TextHolder paragraph, String newText) {
        this.paragraph = paragraph;
        this.newText = newText;
    }

    @Override
    public void execute() {
        oldText = paragraph.getText();
        paragraph.setText(newText);
    }

    @Override
    public void unexecute() {
        paragraph.setText(oldText);
    }
}
