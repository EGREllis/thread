package net.thread.classic;

import net.thread.model.Adder;
import net.thread.model.Counter;
import net.thread.model.LockAdder;

public class LockAdderTrial extends TrialTemplate {
    @Override
    protected Counter getCounter() {
        return new Counter();
    }

    @Override
    protected Adder getAdder(Counter counter, int toAdd) {
        return new LockAdder(counter, toAdd);
    }

    @Override
    protected String getTrialName() {
        return "lock adder";
    }
}
