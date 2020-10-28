package net.thread.model;

public class LockIncrementAdder extends Adder {
    private final Counter counter;

    public LockIncrementAdder(Counter counter, int toAdd) {
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
