package net.thread.model;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MapReduceAdder implements Callable<CountSplit> {
    private final Counter counter;
    private final int toAdd;
    private final CountDownLatch latch;

    public MapReduceAdder(Counter counter, int toAdd, CountDownLatch latch) {
        this.counter = counter;
        this.toAdd = toAdd;
        this.latch = latch;
    }

    @Override
    public CountSplit call() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < toAdd; i++) {
            counter.increment();
        }
        long endTime = System.currentTimeMillis();
        latch.countDown();
        return new CountSplit(startTime, endTime, counter.getCount());
    }
}
