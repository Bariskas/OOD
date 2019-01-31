package Editor;

public class Paragraph {
    private String text;

    public Paragraph() {
        text = "";
    }

    public Paragraph(String inputText) {
        text = inputText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return "Paragraph: " + text;
    }

    public String toHtmlString() {
        return "<p>" + EscapeUtils.escapeHtml(text) + "</p>";
    }
}
