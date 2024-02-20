package main.java.bounce;

public class BallThread extends Thread {
    private final Ball b;

    public BallThread(Ball ball) {
        b = ball;
    }

    @Override
    public void run() {
        try {
            while (b.getIsAlive()) {
                b.move();
                Thread.sleep(5);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
