package net.thread.concurrent.model;

import net.thread.model.Counter;

import java.util.AbstractQueue;
import java.util.concurrent.LinkedTransferQueue;

public class ConcurrentCounter implements Counter {
    private static final Integer ZERO = 0;
    private AbstractQueue<Integer> queue = new LinkedTransferQueue<>();

    @Override
    public void increment() {
        queue.add(ZERO);
    }

    @Override
    public int getCount() {
        return queue.size();
    }
}
