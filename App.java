import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JPanel implements ActionListener, Constants {
    public App() { // Constructor
        this.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT)); // Set window size
        this.setBackground(Color.BLACK); // Set background color
        this.setFocusable(true); // Allows the window to be focused
        start(); // Calls the start() method
    }
    // Variables
    Timer timer;
    List<Object> objects = new ArrayList<>();

    // Starts the game and initializes variables and objects to their default values and states
    public void start() {
        timer = new Timer(DELAY, this);
        timer.start();
        objects.add(new Object(400, 300, (int) Math.pow(10,30), Color.WHITE));
        objects.add(new Object(400, 100, (int) Math.pow(10,20), Color.RED));
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
            object.applyForce(objects);
        }
    }
}