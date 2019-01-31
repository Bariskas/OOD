package Editor;

public class EscapeUtils {
    public static String escapeHtml(String data) {
        return data.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
