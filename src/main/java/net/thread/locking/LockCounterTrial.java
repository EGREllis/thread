package net.thread.locking;

import net.thread.model.*;

public class LockCounterTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new LockCounter();
    }

    @Override
    protected UnsafeAdder getAdder(Counter counter, int toAdd) {
        return new UnsafeAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "lock counter";
    }
}
