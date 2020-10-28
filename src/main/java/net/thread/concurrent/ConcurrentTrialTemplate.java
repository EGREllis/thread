package net.thread.concurrent;

import net.thread.model.*;
import net.thread.util.LatchedCallableToRunable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static net.thread.util.Constants.PER_ADDER;

public abstract class ConcurrentTrialTemplate implements Callable<TrialResult> {
    @Override
    public TrialResult call() {
        Counter counter = getCounter();
        int processors = Runtime.getRuntime().availableProcessors();

        CountDownLatch latch = new CountDownLatch(processors);

        List<LatchedCallableToRunable<Split>> callable = new ArrayList<>();
        for (int i = 0; i < processors; i++) {
            callable.add(new LatchedCallableToRunable<>(getAdder(counter, PER_ADDER), latch));
        }

        ExecutorService service = Executors.newFixedThreadPool(processors);
        System.out.println(String.format("Running \"%1$s\" concurrent trial on %2$d processors", getTrialName(), processors));
        long trialStart = System.currentTimeMillis();
        for (int i = 0; i < processors; i++) {
            service.submit(callable.get(i));
        }

        // Use countdown latch to awake on completion
        try {
            latch.await();
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
            ie.printStackTrace();
            System.err.println("Main thread interrupted!");
        }
        long trialStop = System.currentTimeMillis();
        long elapsedTime = trialStop - trialStart;

        long timeConsumed = 0L;
        for(int i = 0; i < callable.size(); i++) {
            Split split = callable.get(i).getResult();
            timeConsumed += split.getTimeConsumed();
        }
        service.shutdown();

        return new TrialResult(getTrialName(), elapsedTime, timeConsumed, processors * PER_ADDER, counter.getCount());
    }

    protected abstract Counter getCounter();
    protected abstract UnsafeAdder getAdder(Counter counter, int toAdd);
    protected abstract String getTrialName();
}
