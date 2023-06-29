import javax.swing.*;
import java.util.Scanner;

public class Main extends JFrame {

    Main(int maxObjects, boolean colliding, float g) { // Constructor
        this.setResizable(false); // Prevents window from being resized
        this.add(new App(maxObjects, colliding, g)); // Adds the App.java class to the JFrame
        this.setTitle("Gravitation Attraction Simulator"); // Sets the title of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows the window to be closed
        this.pack(); // Sizes the window so that all its contents are at or above their preferred sizes
        this.setVisible(true); // Makes the window visible
        this.setLocationRelativeTo(null); // Centers the window
        this.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true"); // Enables OpenGL

        Scanner scanner = new Scanner(System.in);

        System.out.println("How many objects would you like to simulate?");
        int maxObjects = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Would you like objects to collide? (y/n)");
        boolean colliding = scanner.nextLine().equals("y");

        System.out.println("How big would you like gravity to be? (10 is recommended)");
        float g = scanner.nextInt() / 100f;

        scanner.close();

        new Main(maxObjects, colliding, g); // Creates a new instance of the Main class
    }
}