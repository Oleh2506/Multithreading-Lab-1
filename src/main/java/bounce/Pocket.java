package main.java.bounce;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Pocket {
    private final int x;
    private final int y;
    public static final int RADIUS = 20;

    public Pocket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(x, y, RADIUS*2, RADIUS*2));
    }

    public boolean isBallPotted(int x, int y, int r) {
        double distance = Point2D.distance(x + r, y + r,
                this.x + RADIUS, this.y + RADIUS);
        return distance <= r * 2.5;
    }
}
