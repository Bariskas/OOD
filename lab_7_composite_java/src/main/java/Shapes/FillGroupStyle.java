package Shapes;

import java.util.ArrayList;

public class FillGroupStyle extends GroupShapeStyle {
    public FillGroupStyle(ArrayList<Shape> shapes) {
        super(shapes);
    }

    @Override
    protected Style getConcreteStyle(Shape shape) {
        return shape.getFillStyle();
    }
}
