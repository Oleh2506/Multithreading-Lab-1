package main.java.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockObjectCounter implements Counter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    @Override
    public int getCount() { return this.count; }

    @Override
    public synchronized void increment() {
        lock.lock();
        count++;
        lock.unlock();
    }

    @Override
    public synchronized void decrement() {
        lock.lock();
        count--;
        lock.unlock();
    }
}
