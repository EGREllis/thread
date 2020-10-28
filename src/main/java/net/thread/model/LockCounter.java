package net.thread.model;

public class LockCounter extends UnsafeCounter {
    @Override
    public void increment() {
        synchronized (this) {
            super.increment();
        }
    }
}
