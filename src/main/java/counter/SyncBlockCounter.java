package main.java.counter;

public class SyncBlockCounter implements Counter {
    private int count;
    private final Object monitor = new Object();

    @Override
    public void increment() {
        synchronized (monitor) {
            this.count++;
        }
    }

    @Override
    public void decrement() {
        synchronized (monitor) {
            this.count--;
        }
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
