package net.thread.classic;

import net.thread.model.*;

public class LockTrial extends AbstractTrial {
    @Override
    protected Counter getCounter() {
        return new LockCounter();
    }

    @Override
    protected TrialResult getTrialResult(long timeConsumed, long expected, long actual) {
        return new TrialResult("lock counter", timeConsumed, expected, actual);
    }
}
