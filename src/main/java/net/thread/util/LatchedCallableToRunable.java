package net.thread.util;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class LatchedCallableToRunable<T> implements Runnable {
    private CountDownLatch latch;
    private final Callable<T> callable;
    private T result;

    public LatchedCallableToRunable(Callable<T> callable, CountDownLatch latch) {
        this.callable = callable;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
            latch.countDown();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public T getResult() {
        return result;
    }
}
