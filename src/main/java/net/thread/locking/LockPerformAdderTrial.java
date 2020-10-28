package net.thread.locking;

import net.thread.model.Adder;
import net.thread.model.Counter;
import net.thread.model.LockPerformAdder;

public class LockPerformAdderTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new Counter();
    }

    @Override
    protected Adder getAdder(Counter counter, int toAdd) {
        return new LockPerformAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "lock increment adder";
    }
}
