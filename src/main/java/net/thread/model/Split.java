package net.thread.model;

public class Split {
    private final String threadName;
    private final long startTime;
    private final long stopTime;

    public Split(long startTime, long stopTime) {
        this.threadName = Thread.currentThread().getName();
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    @Override
    public String toString() {
        return String.format("Thread %1$s completed in %2$d/ms", threadName, stopTime - startTime);
    }
}
