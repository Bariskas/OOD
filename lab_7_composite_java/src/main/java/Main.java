import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        frame.add(new City());
        frame.pack();
        frame.setVisible(true);
    }
}
