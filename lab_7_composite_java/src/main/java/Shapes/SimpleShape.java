package Shapes;

abstract class SimpleShape implements Shape {
    protected Style outlineShapeStyle = new ShapeStyle();
    protected Style fillShapeStyle = new ShapeStyle();

    @Override
    public Style getOutlineStyle() {
        return outlineShapeStyle;
    }

    @Override
    public Style getFillStyle() {
        return fillShapeStyle;
    }

    @Override
    public GroupShape getGroup() {
        return null;
    }
}
