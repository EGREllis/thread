package net.thread.model;

public class LockCounter extends Counter {
    @Override
    public void increment() {
        synchronized (this) {
            super.increment();
        }
    }

    @Override
    public void decrement() {
        synchronized (this) {
            super.decrement();
        }
    }
}
