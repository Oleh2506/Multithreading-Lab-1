package main.java.bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private final ArrayList<Pocket> pockets = new ArrayList<>();
    private final JLabel labelPottedBallsCount;
    private int pottedBalls = 0;

    public BallCanvas() {
        labelPottedBallsCount = new JLabel("0");
        labelPottedBallsCount.setForeground(Color.red);
        labelPottedBallsCount.setFont(new Font("Arial", Font.PLAIN, 24));
        super.add(labelPottedBallsCount, BorderLayout.CENTER);
    }

    public void addBall(Ball b) {
        this.balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        generatePockets();

        for (Pocket p : this.pockets) {
            p.draw(g2);
            for (Ball b : this.balls) {
                if (p.isBallPotted(b.getX(), b.getY(), 10)) {
                    b.setIsAlive(false);
                    this.pottedBalls += 1;
                    this.labelPottedBallsCount.setText("" + this.pottedBalls);
                }
            }
        }

        this.balls.removeIf(x -> !x.getIsAlive());

        for (Ball b : this.balls) {
            b.draw(g2);
        }
    }

    private void generatePockets() {
        pockets.clear();
        pockets.add(new Pocket(-20, -20, 20));
        pockets.add(new Pocket(-20, this.getHeight() - 20, 20));
        pockets.add(new Pocket(this.getWidth() - 20, -20, 20));
        pockets.add(new Pocket(this.getWidth() - 20, this.getHeight() - 20, 20));
        pockets.add(new Pocket(this.getWidth() / 2 - 20, -20, 20));
        pockets.add(new Pocket(this.getWidth() / 2 - 20, this.getHeight() - 20, 20));
    }
}

