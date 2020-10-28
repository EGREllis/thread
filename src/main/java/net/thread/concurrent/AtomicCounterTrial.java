package net.thread.concurrent;

import net.thread.locking.TrialTemplate;
import net.thread.model.concurrent.AtomicCounter;
import net.thread.model.Counter;
import net.thread.model.UnsafeAdder;

public class AtomicCounterTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new AtomicCounter();
    }

    @Override
    protected UnsafeAdder getAdder(Counter counter, int toAdd) {
        return new UnsafeAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "atomic counter";
    }
}
