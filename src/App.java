import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JPanel implements ActionListener, Constants {
    public App(int maxObjects, boolean colliding, float g) { // Constructor
        this.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT)); // Set window size
        this.setBackground(Color.BLACK); // Set background color
        this.setFocusable(true); // Allows the window to be focused
        this.maxObjects = maxObjects;
        this.colliding = colliding;
        this.g = g;
        start(); // Calls the start() method
    }
    // Variables
    Timer timer;
    List<Object> objects = new ArrayList<>();
    List<Object> objectsToRemove = new ArrayList<>();
    private final int maxObjects;
    private final boolean colliding;
    private final float g;


    // Starts the game and initializes variables and objects to their default values and states
    public void start() {
        timer = new Timer(DELAY, this);
        timer.start();
        for (int i = 0; i < maxObjects; i++) {
            objects.add(new Object((float) (Math.random() * Constants.WIDTH), (float) (Math.random() * Constants.HEIGHT), (float) (Math.random() * 1000), colors[(int) (Math.random() * colors.length)]));
        }
//        objects.add(new Object(400, 300, 1000000, Color.WHITE));
    }
    // Paints the JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object object : objects) {
            object.draw(g);
        }
    }


    // Repaints the JPanel
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
        for (Object object : objects) {
            object.applyForce(objects, objectsToRemove, colliding, g);
        }
        objects.removeAll(objectsToRemove);
    }
}