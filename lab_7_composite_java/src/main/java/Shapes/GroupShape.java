package Shapes;

import java.awt.*;
import java.util.ArrayList;

public class GroupShape implements Shape, Shapes{
    ArrayList<Shape> shapes = new ArrayList<>();
    Style outlineShapeStyle = new OutlineGroupStyle(shapes);
    Style fillShapeStyle = new FillGroupStyle(shapes);

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
        return this;
    }

    @Override
    public FrameData getFrame() {
        int left = Integer.MAX_VALUE;
        int bottom = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int top = Integer.MIN_VALUE;

        for (Shape shape : shapes)
        {
            FrameData frameData = shape.getFrame();
            left = Math.min(left, frameData.left);
            right = Math.max(right, frameData.left + frameData.width);
            bottom = Math.min(bottom, frameData.top);
            top = Math.max(top, frameData.top + frameData.height);
        }

        return shapes.size() > 0 ? new FrameData(left, bottom, right - left, top - bottom) : new FrameData(0, 0, 0, 0);
    }

    @Override
    public void setFrame(FrameData newFrame) {
        FrameData oldFrame = getFrame();

        for (Shape shape : shapes)
        {
            FrameData shapeFrame = shape.getFrame();
            shapeFrame.left = (int) (newFrame.left + (shapeFrame.left - oldFrame.left) / ((double)oldFrame.width / newFrame.width));
            shapeFrame.top = (int) (newFrame.top + (shapeFrame.top - oldFrame.top) / ((double)oldFrame.height / newFrame.height));
            shapeFrame.width = (int)(shapeFrame.width * ((double)newFrame.width / oldFrame.width));
            shapeFrame.height = (int)(shapeFrame.height * ((double)newFrame.height / oldFrame.height));
            shape.setFrame(shapeFrame);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        for (Shape shape : shapes) {
            shape.draw(graphics);
        }
    }

    @Override
    public int getShapeCount() {
        return shapes.size();
    }

    @Override
    public void removeShapeAtIndex(int index) {
        shapes.remove(index);
    }

    @Override
    public Shape getShapeAtIndex(int index) {
        return shapes.get(index);
    }

    @Override
    public void insertShape(Shape shape) {
        shapes.add(shape);
    }
}
