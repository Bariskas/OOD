package Shapes;

public interface Shapes {
    int getShapeCount();
    void insertShape(Shape shape);
    void removeShapeAtIndex(int index);
    Shape getShapeAtIndex(int index);
}
