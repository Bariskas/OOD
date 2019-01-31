package Editor;

public class DocumentItem {
    private Image image;
    private Paragraph paragraph;

    public DocumentItem(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public DocumentItem(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public String toString() {
        return image != null ? image.toString() : paragraph.toString();
    }

    public String toHtmlString() {
        return image != null ? image.toHtmlString() : paragraph.toHtmlString();
    }
}
