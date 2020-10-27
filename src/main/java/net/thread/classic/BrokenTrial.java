package net.thread.classic;

import net.thread.model.Counter;
import net.thread.model.TrialResult;

public class BrokenTrial extends AbstractTrial {

    @Override
    protected Counter getCounter() {
        return new Counter();
    }

    @Override
    protected TrialResult getTrialResult(long timeConsumed, long expected, long actual) {
        return new TrialResult("broken", timeConsumed, expected, actual);
    }
}
