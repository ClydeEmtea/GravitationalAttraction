import java.awt.*;
import java.util.List;

public class Object {
    private float x;
    private float y;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private double mass;
    private float radius;
    private final Color color;
    private final int maxVelocity;

    public Object(float x, float y, float mass, Color color) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.color = color;
        this.radius = (float) Math.pow(mass, (double) 1 /3);
        this.maxVelocity = (int) (80 / radius);
    }

    private float[] attraction(Object object, float g) {
        float[] force = new float[2];
        float distance = (float) Math.pow(Math.pow(object.getX() - x, 2) + Math.pow(object.getY() - y, 2), 0.5);
        float forceMagnitude = (float) (g * mass * object.getMass() / (float) Math.pow(distance, 2));
        float angle = (float) Math.atan2(object.getY() - y, object.getX() - x);
        force[0] = (float) (forceMagnitude * Math.cos(angle));
        force[1] = (float) (forceMagnitude * Math.sin(angle));
        return force;
    }

    public void applyForce(List<Object> objects, List<Object> objectsToRemove, boolean colliding, float g) {
        float totalForceX = 0;
        float totalForceY = 0;


        for (Object object : objects) {
            if (object != this) {
                float[] force = attraction(object, g);
                totalForceX += force[0];
                totalForceY += force[1];
                float distance = (float) Math.pow(Math.pow(object.getX() - x, 2) + Math.pow(object.getY() - y, 2), 0.5);
                if (colliding && distance <= radius + object.radius && this.mass >= object.getMass()) {
                    objectsToRemove.add(object);
                    mass += object.getMass();
                    radius = (float) Math.pow(mass, 1.0 / 3);
                    xVelocity = (float) ((xVelocity * mass + object.getXVelocity() * object.getMass()) / (mass + object.getMass()));
                    yVelocity = (float) ((yVelocity * mass + object.getYVelocity() * object.getMass()) / (mass + object.getMass()));
                }
            }
        }


        xVelocity += totalForceX / mass;
        yVelocity += totalForceY / mass;

        if (xVelocity > maxVelocity) xVelocity = maxVelocity;
        if (xVelocity < -maxVelocity) xVelocity = -maxVelocity;
        if (yVelocity > maxVelocity) yVelocity = maxVelocity;
        if (yVelocity < -maxVelocity) yVelocity = -maxVelocity;

        x += xVelocity;
        y += yVelocity;

        if (x + radius >= Constants.WIDTH) {
            x = Constants.WIDTH - radius;
            xVelocity = (float) (-0.5 * xVelocity);
        } else if (x - radius <= 0) {
            x = radius;
            xVelocity = (float) (-0.5 * xVelocity);
        } else if (y + radius >= Constants.HEIGHT) {
            y = Constants.HEIGHT - radius;
            yVelocity = (float) (-0.5 * yVelocity);
        } else if (y - radius <= 0) {
            y = radius;
            yVelocity = (float) (-0.5 * yVelocity);
        }
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public double getMass() {
        return mass;
    }
    public float getXVelocity() {
        return xVelocity;
    }
    public float getYVelocity() {
        return yVelocity;
    }
}
