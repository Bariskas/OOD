package Shapes;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GroupShapeTest {

    @Test
    public void testGroupShapeAsContainer() {
        Rectangle rectangle = new Rectangle(new Vertex(100, 400), 100, 60);
        rectangle.getFillStyle().setColor(Color.ORANGE);
        rectangle.getOutlineStyle().setColor(Color.BLACK);
        rectangle.getFillStyle().enable(true);
        rectangle.getOutlineStyle().enable(true);

        Triangle triangle = new Triangle(new Vertex(90, 400), new Vertex(150, 370), new Vertex(210, 400));
        triangle.getFillStyle().setColor(Color.ORANGE);
        triangle.getOutlineStyle().setColor(Color.BLACK);
        triangle.getFillStyle().enable(true);
        triangle.getOutlineStyle().enable(true);

        GroupShape group = new GroupShape();
        group.insertShape(rectangle);
        group.insertShape(triangle);
        assertEquals(group.getShapeCount(), 2);
        assertEquals(group.getShapeAtIndex(1), triangle);
        group.removeShapeAtIndex(1);
        assertEquals(group.getShapeCount(), 1);
    }

    @Test
    public void testGroupShapeChangeColor() {
        Rectangle rectangle = new Rectangle(new Vertex(100, 400), 100, 60);
        rectangle.getFillStyle().setColor(Color.ORANGE);
        rectangle.getOutlineStyle().setColor(Color.BLACK);
        rectangle.getFillStyle().enable(true);
        rectangle.getOutlineStyle().enable(true);

        Triangle triangle = new Triangle(new Vertex(90, 400), new Vertex(150, 370), new Vertex(210, 400));
        triangle.getFillStyle().setColor(Color.ORANGE);
        triangle.getOutlineStyle().setColor(Color.BLACK);
        triangle.getFillStyle().enable(true);
        triangle.getOutlineStyle().enable(true);

        GroupShape group = new GroupShape();
        group.insertShape(rectangle);
        group.insertShape(triangle);
        group.getFillStyle().setColor(Color.YELLOW);
        group.getOutlineStyle().setColor(Color.YELLOW);
        assertEquals(group.getFillStyle().getColor(), rectangle.getFillStyle().getColor());
        assertEquals(group.getFillStyle().getColor(), triangle.getFillStyle().getColor());
        assertEquals(group.getOutlineStyle().getColor(), rectangle.getFillStyle().getColor());
        assertEquals(group.getOutlineStyle().getColor(), triangle.getFillStyle().getColor());
    }

    @Test
    public void testGroupShapeChangeFrame() {
        Rectangle rectangle = new Rectangle(new Vertex(100, 400), 100, 100);
        rectangle.getFillStyle().setColor(Color.ORANGE);
        rectangle.getOutlineStyle().setColor(Color.BLACK);
        rectangle.getFillStyle().enable(true);
        rectangle.getOutlineStyle().enable(true);

        Triangle triangle = new Triangle(new Vertex(100, 400), new Vertex(150, 300), new Vertex(200, 400));
        triangle.getFillStyle().setColor(Color.ORANGE);
        triangle.getOutlineStyle().setColor(Color.BLACK);
        triangle.getFillStyle().enable(true);
        triangle.getOutlineStyle().enable(true);

        GroupShape groupShape = new GroupShape();
        groupShape.insertShape(rectangle);
        groupShape.insertShape(triangle);
        FrameData groupFrameData = groupShape.getFrame();
        groupFrameData.height = groupFrameData.height + 100;
        groupFrameData.width = groupFrameData.width + 100;
        groupShape.setFrame(groupFrameData);

        FrameData triangleFrame = triangle.getFrame();
        assertEquals(triangleFrame.width, 200);
        assertEquals(triangleFrame.height, 150);
        FrameData rectangleFrame = rectangle.getFrame();
        assertEquals(rectangleFrame.width, 200);
        assertEquals(rectangleFrame.height, 150);
    }

    @Test
    public void testGroupShapeGetGroup() {
        GroupShape groupShape = new GroupShape();

        assertNotEquals(groupShape.getGroup(), null);
    }
}