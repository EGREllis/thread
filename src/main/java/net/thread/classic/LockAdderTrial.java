package net.thread.classic;

import net.thread.model.Adder;
import net.thread.model.Counter;
import net.thread.model.LockAdder;
import net.thread.model.TrialResult;

public class LockAdderTrial extends AbstractTrial {
    @Override
    protected String getMessage(int CORRECT_RESULT, Counter counter) {
        String message;
        if(CORRECT_RESULT ==counter.getCount()) {
            message = String.format("Received the correct result! (Which we expect)! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        } else {
            message = String.format("Received an incorrect result! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        }
        return message;
    }

    @Override
    protected Counter getCounter() {
        return new Counter();
    }

    @Override
    protected Adder getAdder(Counter counter, int toAdd) {
        return new LockAdder(counter, toAdd);
    }

    @Override
    protected TrialResult getTrialResult(long timeConsumed, long expected, long actual) {
        return new TrialResult("lock adder", timeConsumed, expected, actual);
    }
}
