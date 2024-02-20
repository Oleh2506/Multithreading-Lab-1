package main.java.counter;

public class BlockObjectCounter implements Counter {
    private int count = 0;
    private boolean dec = false;

    @Override
    public int getCount() { return this.count; }

    @Override
    public synchronized void increment() {
        while (dec) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        dec = true;
        count++;
        notifyAll();
    }

    @Override
    public synchronized void decrement() {
        while (!dec) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        dec = false;
        count--;
        notifyAll();
    }
}
