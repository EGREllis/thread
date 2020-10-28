package net.thread.locking;

import net.thread.model.Counter;
import net.thread.model.UnsafeAdder;
import net.thread.model.UnsafeCounter;
import net.thread.model.LockPerformAdder;

public class LockPerformAdderTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new UnsafeCounter();
    }

    @Override
    protected UnsafeAdder getAdder(Counter counter, int toAdd) {
        return new LockPerformAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "lock perform adder";
    }
}
