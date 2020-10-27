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

    @Override
    protected String getMessage(final int CORRECT_RESULT, Counter counter) {
        String message;
        if(CORRECT_RESULT ==counter.getCount()) {
            message = String.format("Received the correct result! (Which we expect)! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        } else {
            message = String.format("Received an incorrect result! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        }
        return message;
    }
}
