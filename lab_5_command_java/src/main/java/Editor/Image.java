package Editor;

public class Image implements Resizable {
    private String originPath;
    private String actualPath;
    private Integer width;
    private Integer height;

    public Image() {
        originPath = "";
        width = 0;
        height = 0;
    }

    public Image(String originPath, int width, int height) {
        this.originPath = originPath;
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setActualPath(String actualPath) {
        this.actualPath = actualPath;
    }

    public String getOriginPath() {
        return originPath;
    }

    public String toString() {
        return "Image: " + width + " " + height + " " + (actualPath == null ? originPath : actualPath);
    }

    public String toHtmlString() {
        return "<img src=\"" + EscapeUtils.escapeHtml(originPath) + "\" width=\"" + width + "\" height=\"" + height + "\">";
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
