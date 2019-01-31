import Shapes.FrameData;
import Shapes.GroupShape;
import Shapes.Vertex;

import javax.swing.*;
import java.awt.*;

public class City extends JPanel {
    public City() {
        setBorder(BorderFactory.createLineBorder(java.awt.Color.black));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintNature(g);
        paintSun(g);
        paintHouse1(g);
        paintHouse2(g);
    }

    private void paintNature(Graphics g) {
        Shapes.Rectangle rectangle = new Shapes.Rectangle(new Vertex(0, 0), 800, 400);
        rectangle.getFillStyle().setColor(Color.BLUE);
        rectangle.getFillStyle().enable(true);
        rectangle.draw(g);
        Shapes.Rectangle grass = new Shapes.Rectangle(new Vertex(0, 400), 800, 400);
        grass.getFillStyle().setColor(Color.GREEN);
        grass.getFillStyle().enable(true);
        grass.draw(g);
    }

    private void paintSun(Graphics g) {
        Shapes.Ellipse sun = new Shapes.Ellipse(new Vertex(600, 100), 50, 50);
        sun.getFillStyle().setColor(Color.YELLOW);
        sun.getFillStyle().enable(true);
        sun.draw(g);
    }

    private void paintHouse1(Graphics g) {
        Shapes.Rectangle rectangle = new Shapes.Rectangle(new Vertex(100, 400), 100, 60);
        rectangle.getFillStyle().setColor(Color.ORANGE);
        rectangle.getOutlineStyle().setColor(Color.BLACK);
        rectangle.getFillStyle().enable(true);
        rectangle.getOutlineStyle().enable(true);
        rectangle.draw(g);

        Shapes.Triangle triangle = new Shapes.Triangle(new Vertex(90, 400), new Vertex(150, 370), new Vertex(210, 400));
        triangle.getFillStyle().setColor(Color.ORANGE);
        triangle.getOutlineStyle().setColor(Color.BLACK);
        triangle.getFillStyle().enable(true);
        triangle.getOutlineStyle().enable(true);
        triangle.draw(g);
    }

    private void paintHouse2(Graphics g) {
        Shapes.Rectangle rectangle = new Shapes.Rectangle(new Vertex(100, 400), 100, 60);
        rectangle.getFillStyle().setColor(Color.ORANGE);
        rectangle.getOutlineStyle().setColor(Color.BLACK);
        rectangle.getFillStyle().enable(true);
        rectangle.getOutlineStyle().enable(true);

        Shapes.Triangle triangle = new Shapes.Triangle(new Vertex(90, 400), new Vertex(150, 370), new Vertex(210, 400));
        triangle.getFillStyle().setColor(Color.ORANGE);
        triangle.getOutlineStyle().setColor(Color.BLACK);
        triangle.getFillStyle().enable(true);
        triangle.getOutlineStyle().enable(true);

        GroupShape house2 = new GroupShape();
        house2.insertShape(rectangle);
        house2.insertShape(triangle);
        FrameData house2FrameData = house2.getFrame();
        house2FrameData.left = 450;
        house2FrameData.top = 450;
        house2FrameData.height = house2FrameData.height + 100;
        house2FrameData.width = house2FrameData.width + 100;
        house2.getFillStyle().setColor(Color.YELLOW);
        house2.setFrame(house2FrameData);
        house2.draw(g);
    }
}
