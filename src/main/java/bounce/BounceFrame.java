package main.java.bounce;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 750;
    public static final int HEIGHT = 400;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        this.canvas = new BallCanvas();

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonSpawnBlueBall = new JButton("Spawn Blue");
        JButton buttonSpawnRedBall = new JButton("Spawn Red");
        JButton buttonStop = new JButton("Stop");
        JButton buttonTestPriority = new JButton("Test Priority");
        JButton buttonTestJoin = new JButton("Test Join");

        buttonTestPriority.addActionListener(e -> {
            Ball redBall = new Ball(canvas, Color.red, 30, 50);
            BallThread redThread = new BallThread(redBall);
            redThread.setPriority(Thread.MAX_PRIORITY);
            redThread.start();

            for (int i = 0; i < 100; i++) {
                Ball blueBall = new Ball(canvas, Color.blue, 30, 50);
                canvas.addBall(blueBall);
                BallThread blueThread = new BallThread(blueBall);
                blueThread.setPriority(Thread.MIN_PRIORITY);
                blueThread.start();
            }

            canvas.addBall(redBall);
        });

        buttonSpawnBlueBall.addActionListener(e -> {
            Ball b = new Ball(canvas, Color.blue);
            canvas.addBall(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        });

        buttonSpawnRedBall.addActionListener(e -> {
            Ball b = new Ball(canvas, Color.red);
            canvas.addBall(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        });

        buttonTestJoin.addActionListener(e -> {
            Ball blueBall = new Ball(canvas, Color.blue, 30, 50);
            Ball redBall = new Ball(canvas, Color.red, 30, 50);
            canvas.addBall(blueBall);
            canvas.addBall(redBall);

            BallThread threadBlue = new BallThread(blueBall);
            BallThread threadRed = new BallThread(redBall);

            threadBlue.start();

            Thread joinThread = new Thread(() -> {
                try {
                    threadBlue.join();
                    threadRed.start();
                } catch (InterruptedException ignored) {
                }
            });
            joinThread.start();

        });

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonSpawnBlueBall);
        buttonPanel.add(buttonSpawnRedBall);
        buttonPanel.add(buttonTestPriority);
        buttonPanel.add(buttonTestJoin);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}