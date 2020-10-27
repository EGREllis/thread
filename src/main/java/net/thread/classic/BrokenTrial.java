package net.thread.classic;

import net.thread.model.Adder;
import net.thread.model.Counter;
import net.thread.model.TrialResult;

public class BrokenTrial extends AbstractTrial {

    @Override
    protected Counter getCounter() {
        return new Counter();
    }

    @Override
    protected Adder getAdder(Counter counter, int toAdd) {
        return new Adder(counter, toAdd);
    }

    @Override
    protected TrialResult getTrialResult(long timeConsumed, long expected, long actual) {
        return new TrialResult("broken", timeConsumed, expected, actual);
    }

    @Override
    protected String getMessage(final int CORRECT_RESULT, Counter counter) {
        String message;
        if(CORRECT_RESULT ==counter.getCount()) {
            message = String.format("Received the correct result! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        } else {
            message = String.format("Received an incorrect result (Which we expect)! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        }
        return message;
    }
}
