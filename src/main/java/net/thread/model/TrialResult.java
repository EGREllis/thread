package net.thread.model;

public class TrialResult {
    private final String trialName;
    private final long timeConsumed;
    private final long expected;
    private final long actual;

    public TrialResult(String trialName, long timeConsumed, long expected, long actual) {
        this.trialName = trialName;
        this.timeConsumed = timeConsumed;
        this.expected = expected;
        this.actual = actual;
    }

    public long getTimeConsumed() {
        return timeConsumed;
    }

    public long getExpected() {
        return expected;
    }

    public long getActual() {
        return actual;
    }

    @Override
    public String toString() {
        return String.format("Trial: %1$s Time: %2$d/ms Expected: %3$d Actual: %4$d Error rate: %5$f/pc", trialName, timeConsumed, expected, actual, 100.0*(expected - actual)/expected);
    }
}
