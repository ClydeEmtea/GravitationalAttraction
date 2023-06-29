import java.awt.*;
import java.util.List;

public class Object {
    private int x;
    private int y;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private final double mass;
    private final int radius = 10;
    private final Color color;

    public Object(int x, int y, int mass, Color color) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.color = color;
    }

    private float[] attraction(Object object) {
        float[] force = new float[2];
        float distance = (float) Math.pow(Math.pow(object.getX() - x, 2) + Math.pow(object.getY() - y, 2), 0.5);
        float forceMagnitude = (float) (Constants.G * mass * object.getMass() / (float) Math.pow(distance, 2));
        float angle = (float) Math.atan2(object.getY() - y, object.getX() - x);
        force[0] = (float) (forceMagnitude * Math.cos(angle));
        force[1] = (float) (forceMagnitude * Math.sin(angle));
        return force;
    }

    public void applyForce(List<Object> objects) {
        float totalForceX = 0;
        float totalForceY = 0;
        for (Object object : objects) {
            if (object != this) {
                float[] force = attraction(object);
                totalForceX += force[0];
                totalForceY += force[1];
                System.out.println(totalForceX + " " + totalForceY);
            }
        }
        xVelocity += totalForceX / mass;
        yVelocity += totalForceY / mass;
        System.out.println(xVelocity + " " + yVelocity);

        x += xVelocity;
        y += yVelocity;
        System.out.println(x + " " + y);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public double getMass() {
        return mass;
    }
}
