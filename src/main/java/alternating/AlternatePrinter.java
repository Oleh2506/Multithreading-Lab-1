package main.java.alternating;

public class AlternatePrinter {
    public static void main(String[] args) {
        Sync sync = new Sync();
        Thread vbarThread = new Thread(new SyncCharPrinter('|', sync));
        Thread hbarThread = new Thread(new SyncCharPrinter('-', sync));

        //Thread vbarThread = new Thread(new AsyncCharPrinter('|'));
        //Thread hbarThread = new Thread(new AsyncCharPrinter('-'));
        vbarThread.start();
        hbarThread.start();
    }
}

class AsyncCharPrinter implements Runnable {
    private final char character;

    public AsyncCharPrinter(char c) {
        this.character = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(this.character);
            }
            System.out.println();
        }
    }
}

class Sync {
    private boolean printVBar = true;
    private int newLineCounter = 0;

    public synchronized void printChar(char c) {
        while ((c != '|' && this.printVBar) || (c == '|' && !this.printVBar)) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }

        newLineCounter += 1;
        this.printVBar = !this.printVBar;
        System.out.print(c);

        if (newLineCounter % 100 == 0) {
            System.out.println();
        }

        notifyAll();
    }
}

class SyncCharPrinter implements Runnable {
    private final char character;
    private final Sync sync;

    public SyncCharPrinter(char c, Sync sync) {
        this.character = c;
        this.sync = sync;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            this.sync.printChar(this.character);
        }
    }
}