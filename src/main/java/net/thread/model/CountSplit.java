package net.thread.model;

public class CountSplit extends Split {
    private final int count;
    public CountSplit(long startTime, long stopTime, int count) {
        super(startTime, stopTime);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
