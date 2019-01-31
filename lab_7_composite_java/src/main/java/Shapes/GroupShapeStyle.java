package Shapes;

import java.awt.*;
import java.util.ArrayList;

abstract class GroupShapeStyle implements Style {
    private ArrayList<Shape> shapes;

    public GroupShapeStyle(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public boolean isEnabled() {
        if (shapes.size() == 0) {
            return false;
        }

        for (Shape shape : shapes) {
            Style style = getConcreteStyle(shape);
            if (!style.isEnabled()) {
                return false;
            }
        }

        return true;
    }

    public void enable(boolean isEnable) {
        for (Shape shape : shapes) {
            Style style = getConcreteStyle(shape);
            style.enable(isEnable);
        }
    }

    public Color getColor() {
        if (shapes.size() == 0) {
            return new Color(Color.OPAQUE);
        }

        Color firstColor = getConcreteStyle(shapes.get(0)).getColor();

        for (Shape shape : shapes) {
            Style style = getConcreteStyle(shape);
            if (!style.getColor().equals(firstColor)) {
                return null;
            }
        }

        return firstColor;
    }

    public void setColor(Color color) {
        for (Shape shape : shapes) {
            Style style = getConcreteStyle(shape);
            style.setColor(color);
        };
    }

    abstract protected Style getConcreteStyle(Shape shape);
}
