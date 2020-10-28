package net.thread.model;

import java.util.concurrent.Callable;

public class Adder implements Callable<Split>, Runnable {
    private final Counter counter;
    private final int toAdd;

    public Adder(Counter counter, int toAdd) {
        this.counter = counter;
        this.toAdd = toAdd;
    }

    @Override
    public Split call() {
        return perform();
    }

    @Override
    public void run() {
        Split split = perform();
        System.out.println(split);
    }

    protected Split perform() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < toAdd; i++) {
            increment();
        }
        long endTime = System.currentTimeMillis();
        return new Split(startTime, endTime);
    }

    protected void increment() {
        counter.increment();
    }
}
