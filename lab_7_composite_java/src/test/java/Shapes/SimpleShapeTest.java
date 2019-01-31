package Shapes;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SimpleShapeTest {
    @Test
    public void testShapeGetGroup() {
        Rectangle rectangle = new Rectangle(new Vertex(100, 400), 100, 60);
        rectangle.getFillStyle().setColor(Color.ORANGE);
        rectangle.getOutlineStyle().setColor(Color.BLACK);
        rectangle.getFillStyle().enable(true);
        rectangle.getOutlineStyle().enable(true);

        assertEquals(rectangle.getGroup(), null);
    }

    @Test
    public void testShapeChangeColor() {
        Rectangle rectangle = new Rectangle(new Vertex(100, 400), 100, 60);
        rectangle.getFillStyle().setColor(Color.ORANGE);
        rectangle.getFillStyle().enable(true);

        assertEquals(rectangle.getFillStyle().getColor(), Color.ORANGE);
        assertEquals(rectangle.getFillStyle().isEnabled(), true);
        assertEquals(rectangle.getOutlineStyle().getColor(), new Color(Color.OPAQUE));
        assertEquals(rectangle.getOutlineStyle().isEnabled(), false);
    }
}