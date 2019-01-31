package Shapes;

import java.awt.*;

public interface Style {
    boolean isEnabled();

    void enable(boolean isEnable);

    Color getColor();

    void setColor(Color color);
}
