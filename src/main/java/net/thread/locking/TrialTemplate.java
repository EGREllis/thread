package net.thread.locking;

import net.thread.model.*;
import net.thread.util.CallableToRunable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static net.thread.util.Constants.PER_ADDER;
import static net.thread.util.Constants.CALC_DELAY;

public abstract class TrialTemplate implements Callable<TrialResult> {
    @Override
    public TrialResult call() {
        Counter counter = getCounter();

        int processors = Runtime.getRuntime().availableProcessors();
        List<CallableToRunable<Split>> callable = new ArrayList<>();
        for (int i = 0; i < processors; i++) {
            callable.add(new CallableToRunable<>(getAdder(counter, PER_ADDER)));
        }

        List<Thread> threads = new ArrayList<>(processors);
        System.out.println(String.format("Running \"%1$s\" basic trial on %2$d processors", getTrialName(), processors));
        for (int i = 0; i < processors; i++) {
            threads.add(new Thread(callable.get(i)));
        }

        // Start all threads
        long trialStart = System.currentTimeMillis();
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
        long trialStop = System.currentTimeMillis();
        long elapsedTime = trialStop - trialStart;

        // Check the result
        final int CORRECT_RESULT = processors * PER_ADDER;
        String message = getMessage(CORRECT_RESULT, counter);
        System.out.println(message);

        long timeConsumed = 0L;
        for(int i = 0; i < callable.size(); i++) {
            Split split = callable.get(i).getResult();
            timeConsumed += split.getTimeConsumed();
        }

        return new TrialResult(getTrialName(), elapsedTime, timeConsumed, processors * PER_ADDER, counter.getCount());
    }

    protected String getMatchMessage(int expected, Counter counter) {
        return String.format("Received the correct result! (Which we expect)! (expected: %1$d, actual: %2$d)", expected, counter.getCount());
    }

    protected String getMisMatchMessage(int expected, Counter counter) {
        return String.format("Received an incorrect result! (expected: %1$d, actual: %2$d)", expected, counter.getCount());
    }

    private String getMessage(int CORRECT_RESULT, Counter counter) {
        String message;
        if(CORRECT_RESULT ==counter.getCount()) {
            message = getMatchMessage(CORRECT_RESULT, counter);
        } else {
            message = getMisMatchMessage(CORRECT_RESULT, counter);
        }
        return message;
    }

    protected abstract Counter getCounter();
    protected abstract UnsafeAdder getAdder(Counter counter, int toAdd);
    protected abstract String getTrialName();
}
