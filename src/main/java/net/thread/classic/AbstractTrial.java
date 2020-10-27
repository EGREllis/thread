package net.thread.classic;

import net.thread.model.Adder;
import net.thread.model.Counter;
import net.thread.model.Split;
import net.thread.model.TrialResult;
import net.thread.util.CallableToRunable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractTrial implements Callable<TrialResult> {
    private static final int PER_ADDER = 10000000;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final int DELAY_SECONDS = 10;
    private static final long CALC_DELAY = MILLISECONDS_PER_SECOND * DELAY_SECONDS;

    @Override
    public TrialResult call() {
        Counter counter = getCounter();

        int processors = Runtime.getRuntime().availableProcessors();
        List<CallableToRunable<Split>> callable = new ArrayList<>();
        for (int i = 0; i < processors; i++) {
            callable.add(new CallableToRunable<>(getAdder(counter, PER_ADDER)));
        }

        List<Thread> threads = new ArrayList<>(processors);
        System.out.println(String.format("Running broken counter on %1$d processors", processors));
        for (int i = 0; i < processors; i++) {
            threads.add(new Thread(callable.get(i)));
        }

        // Start all threads
        for (int i = 0; i < processors; i++) {
            Thread thread = threads.get(i);
            thread.start();
        }

        // Give them time to complete
        try {
            Thread.sleep(CALC_DELAY);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
            ie.printStackTrace();
            System.err.println("Main thread interrupted!");
        }

        // Check the result
        final int CORRECT_RESULT = processors * PER_ADDER;
        String message = getMessage(CORRECT_RESULT, counter);
        System.out.println(message);

        long timeConsumed = 0L;
        for(int i = 0; i < callable.size(); i++) {
            Split split = callable.get(i).getResult();
            timeConsumed += split.getTimeConsumed();
        }

        return getTrialResult(timeConsumed,processors * PER_ADDER, counter.getCount());
    }

    protected abstract String getMessage(int CORRECT_RESULT, Counter counter);
    protected abstract Counter getCounter();
    protected abstract Adder getAdder(Counter counter, int toAdd);
    protected abstract TrialResult getTrialResult(long timeConsumed, long expected, long actual);
}
