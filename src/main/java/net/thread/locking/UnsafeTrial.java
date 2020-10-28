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

    @Override
    protected String getMatchMessage(int CORRECT_RESULT, Counter counter) {
        return String.format("Received the correct result! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
    }

    @Override
    protected String getMisMatchMessage(int CORRECT_RESULT, Counter counter) {
        return String.format("Received an incorrect result (Which we expect)! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
    }
}
