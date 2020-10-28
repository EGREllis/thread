package net.thread.locking;

import net.thread.model.Adder;
import net.thread.model.Counter;
import net.thread.model.LockIncrementAdder;

public class LockIncrementAdderTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new Counter();
    }

    @Override
    protected Adder getAdder(Counter counter, int toAdd) {
        return new LockIncrementAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "lock increment adder";
    }
}
