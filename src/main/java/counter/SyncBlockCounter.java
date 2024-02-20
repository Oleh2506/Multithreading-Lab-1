package main.java.counter;

public class SyncBlockCounter implements Counter {
    private int count;
    private final Object sync = new Object();

    @Override
    public void increment() {
        synchronized (sync) {
            this.count++;
        }
    }

    @Override
    public void decrement() {
        synchronized (sync) {
            this.count--;
        }
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
