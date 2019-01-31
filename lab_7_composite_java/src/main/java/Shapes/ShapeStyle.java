package Shapes;

import java.awt.*;

public class ShapeStyle implements Style {
    private boolean isEnabled;
    private Color color;

    ShapeStyle() {
        isEnabled = false;
        color = new Color(Color.OPAQUE);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void enable(boolean isEnable) {
        this.isEnabled = isEnable;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
