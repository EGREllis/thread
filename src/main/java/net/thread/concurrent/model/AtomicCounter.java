package net.thread.concurrent.model;

import net.thread.model.Counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void increment() {
        count.incrementAndGet();
    }

    @Override
    public int getCount() {
        return count.get();
    }
}
