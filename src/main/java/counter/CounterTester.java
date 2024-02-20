package main.java.counter;

public class CounterTester {
    public static void main(String[] args) {
        testCounter("Non-Sync Counter: ", new AsyncCounter());
        testCounter("Sync-Block Counter: ", new SyncBlockCounter());
        testCounter("Sync-Method Counter: ", new SyncMethodCounter());
        testCounter("Block Object Counter: ", new BlockObjectCounter());
    }

    static void testCounter(String msg, Counter counter) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ignored) {
        }

        System.out.println(msg + counter.getCount());
    }
}
