package main.java.bounce;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private final Component canvas;
    private static final int X_SIZE = 20;
    private static final int Y_SIZE = 20;
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 2;
    private boolean isAlive = true;
    private final Color color;

    public boolean getIsAlive() { return this.isAlive; }

    public void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public Ball(Component c, Color color) {
        this.canvas = c;
        this.color = color;

        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public Ball(Component c, Color color, int x, int y) {
        this.canvas = c;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(this.color);
        g2.fill(new Ellipse2D.Double(x, y, X_SIZE, Y_SIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + X_SIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - X_SIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + Y_SIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - Y_SIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }
}