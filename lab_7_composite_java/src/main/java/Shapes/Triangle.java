package Shapes;

import java.awt.*;

public class Triangle extends SimpleShape {
    Vertex v1;
    Vertex v2;
    Vertex v3;

    public Triangle(Vertex v1, Vertex v2, Vertex v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    @Override
    public void setFrame(FrameData frame) {
        FrameData oldFrame = getFrame();
        changeVertexForNewFrame(v1, oldFrame, frame);
        changeVertexForNewFrame(v2, oldFrame, frame);
        changeVertexForNewFrame(v3, oldFrame, frame);
    }

    @Override
    public FrameData getFrame() {
        int left = Integer.MAX_VALUE;
        int bottom = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int top = Integer.MIN_VALUE;

        for (Vertex v : new Vertex[]{v1, v2, v3}) {
            left = Math.min(left, v.x);
            right = Math.max(right, v.x);
            bottom = Math.min(bottom, v.y);
            top = Math.max(top, v.y);
        }
        return new FrameData(left, bottom,
                right - left,
                top - bottom);
    }

    @Override
    public void draw(Graphics graphics) {
        Style fillShapeStyle = getFillStyle();
        if (fillShapeStyle.isEnabled()) {
            graphics.setColor(fillShapeStyle.getColor());
            graphics.fillPolygon(new int[]{v1.x, v2.x, v3.x}, new int[]{v1.y, v2.y, v3.y}, 3);
        }

        Style outlineShapeStyle = getOutlineStyle();
        if (outlineShapeStyle.isEnabled()) {
            graphics.setColor(outlineShapeStyle.getColor());
            graphics.drawPolygon(new int[]{v1.x, v2.x, v3.x}, new int[]{v1.y, v2.y, v3.y}, 3);
        }
    }

    private void changeVertexForNewFrame(Vertex vertex, FrameData oldFrame, FrameData newFrame) {
        vertex.x = (int) (newFrame.left + (vertex.x - oldFrame.left) / ((double)oldFrame.width / newFrame.width));
        vertex.y = (int) (newFrame.top + (vertex.y - oldFrame.top) / ((double)oldFrame.height / newFrame.height));
    }
}
