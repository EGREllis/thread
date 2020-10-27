package net.thread.util;

import java.util.concurrent.Callable;

public class CallableToRunable<T> implements Runnable {
    private final Callable<T> callable;
    private T result;

    public CallableToRunable(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public T getResult() {
        return result;
    }
}
