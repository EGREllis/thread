package net.thread.concurrent;

import net.thread.concurrent.model.ConcurrentCounter;
import net.thread.model.Counter;
import net.thread.model.UnsafeAdder;

public class BasicConcurrentTrialTemplate extends ConcurrentTrialTemplate {
    @Override
    protected Counter getCounter() {
        return new ConcurrentCounter();
    }

    @Override
    protected UnsafeAdder getAdder(Counter counter, int toAdd) {
        return new UnsafeAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "concurrent linked transfer queue";
    }
}
