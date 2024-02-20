package main.java.counter;

public class NaiveCounter implements Counter {
    private int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public void decrement() {
        count--;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
