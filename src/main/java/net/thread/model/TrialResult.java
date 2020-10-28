package net.thread.model;

public class TrialResult {
    private final String trialName;
    private final long elapsedTime;
    private final long timeConsumed;
    private final long expected;
    private final long actual;

    public TrialResult(String trialName, long elapsedTime, long timeConsumed, long expected, long actual) {
        this.trialName = trialName;
        this.elapsedTime = elapsedTime;
        this.timeConsumed = timeConsumed;
        this.expected = expected;
        this.actual = actual;
    }

    public long getElapsedTime() {
        return elapsedTime;
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
        return String.format("Trial: \"%1$s\" Elapsed Time: %2$d/ms Time: %3$d/ms Expected: %4$d Actual: %5$d Error rate: %6$f/pc", trialName, elapsedTime, timeConsumed, expected, actual, 100.0*(expected - actual)/expected);
    }
}
