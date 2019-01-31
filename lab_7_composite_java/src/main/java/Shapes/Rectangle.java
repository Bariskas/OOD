package Shapes;

import java.awt.*;

public class Rectangle extends SimpleShape {
    private Vertex topLeft;
    private int width;
    private int height;
    public Rectangle(Vertex topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setFrame(FrameData frame) {
        topLeft.x = frame.left;
        topLeft.y = frame.top;
        width = frame.width;
        height = frame.height;
    }

    @Override
    public FrameData getFrame() {
        return new FrameData(topLeft.x, topLeft.y, width, height);
    }

    @Override
    public void draw(Graphics graphics) {
        Style fillShapeStyle = getFillStyle();
        if (fillShapeStyle.isEnabled()) {
            graphics.setColor(fillShapeStyle.getColor());
            graphics.fillRect(topLeft.x, topLeft.y, width, height);
        }

        Style outlineShapeStyle = getOutlineStyle();
        if (outlineShapeStyle.isEnabled()) {
            graphics.setColor(outlineShapeStyle.getColor());
            graphics.drawRect(topLeft.x, topLeft.y, width, height);
        }
    }
}
