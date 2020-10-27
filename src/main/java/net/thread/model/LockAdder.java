package net.thread.model;

public class LockAdder extends Adder {
    private final Counter counter;

    public LockAdder(Counter counter, int toAdd) {
        super(counter, toAdd);
        this.counter = counter;
    }

    @Override
    protected void increment() {
        synchronized (counter) {
            super.increment();
        }
    }
}
