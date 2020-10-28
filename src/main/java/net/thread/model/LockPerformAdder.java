package net.thread.model;

public class LockPerformAdder extends UnsafeAdder {
    private final Counter counter;

    public LockPerformAdder(Counter counter, int toAdd) {
        super(counter, toAdd);
        this.counter = counter;
    }

    @Override
    protected Split perform() {
        synchronized (counter) {
            return super.perform();
        }
    }
}
