package net.thread.model;

public class Adder implements Runnable {
    private final Counter counter;
    private final int toAdd;

    public Adder(Counter counter, int toAdd) {
        this.counter = counter;
        this.toAdd = toAdd;
    }

    @Override
    public void run() {
        for (int i = 0; i < toAdd; i++) {
            counter.increment();
        }
    }
}
