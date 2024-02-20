package main.java.counter;

public class SyncMethodCounter implements Counter {
    private int count;
    @Override
    public synchronized void increment() {
        this.count++;
    }

    @Override
    public synchronized void decrement() {
        this.count--;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
