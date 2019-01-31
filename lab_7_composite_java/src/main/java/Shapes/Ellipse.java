package Shapes;

import java.awt.*;

public class Ellipse extends SimpleShape {
    private Vertex center;
    private int radiusX;
    private int radiusY;

    public Ellipse(Vertex center, int radiusX, int radiusY) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void setFrame(FrameData frame) {
        center.x = frame.left + (frame.width / 2);
        center.y = frame.top + (frame.height / 2);
        radiusX = frame.width / 2;
        radiusY = frame.height / 2;
    }

    @Override
    public FrameData getFrame() {
        return new FrameData(center.x - radiusX, center.y - radiusY, radiusX * 2, radiusY * 2);
    }

    @Override
    public void draw(Graphics graphics) {
        Style fillShapeStyle = getFillStyle();
        if (fillShapeStyle.isEnabled()) {
            graphics.setColor(fillShapeStyle.getColor());
            graphics.fillOval(center.x, center.y, radiusX * 2, radiusY * 2);
        }

        Style outlineShapeStyle = getOutlineStyle();
        if (outlineShapeStyle.isEnabled()) {
            graphics.setColor(outlineShapeStyle.getColor());
            graphics.drawOval(center.x, center.y, radiusX * 2, radiusY * 2);
        }
    }
}
