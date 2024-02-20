package main.java.bounce;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Pocket {
    private final int x;
    private final int y;
    private final int radius;

    public Pocket(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(x, y, radius*2, radius*2));
    }

    public boolean isBallPotted(int x, int y, int r) {
        double distance = Point2D.distance(x + r, y + r,
                this.x + this.radius, this.y + this.radius);
        return distance <= r * 2.5;
    }
}
