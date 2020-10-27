package net.thread.classic;

import net.thread.model.*;

public class LockCounterTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new LockCounter();
    }

    @Override
    protected Adder getAdder(Counter counter, int toAdd) {
        return new Adder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "lock counter";
    }
}
