package net.thread.locking;

import net.thread.model.Counter;
import net.thread.model.UnsafeAdder;
import net.thread.model.UnsafeCounter;

public class UnsafeTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new UnsafeCounter();
    }

    @Override
    protected UnsafeAdder getAdder(Counter counter, int toAdd) {
        return new UnsafeAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "broken";
    }
}
