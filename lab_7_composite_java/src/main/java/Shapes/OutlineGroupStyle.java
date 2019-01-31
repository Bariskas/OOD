package Shapes;

import java.util.ArrayList;

public class OutlineGroupStyle extends GroupShapeStyle {
    public OutlineGroupStyle(ArrayList<Shape> shapes) {
        super(shapes);
    }

    @Override
    protected Style getConcreteStyle(Shape shape) {
        return shape.getOutlineStyle();
    }
}
