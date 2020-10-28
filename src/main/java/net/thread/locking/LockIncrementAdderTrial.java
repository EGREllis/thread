package net.thread.locking;

import net.thread.model.Counter;
import net.thread.model.UnsafeAdder;
import net.thread.model.UnsafeCounter;
import net.thread.model.LockIncrementAdder;

public class LockIncrementAdderTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new UnsafeCounter();
    }

    @Override
    protected UnsafeAdder getAdder(Counter counter, int toAdd) {
        return new LockIncrementAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "lock increment adder";
    }
}
