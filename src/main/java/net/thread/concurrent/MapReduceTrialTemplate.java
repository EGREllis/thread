package net.thread.concurrent;

import net.thread.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static net.thread.util.Constants.PER_ADDER;

public class MapReduceTrialTemplate implements Callable<TrialResult> {
    @Override
    public TrialResult call() throws Exception {
        int processors = Runtime.getRuntime().availableProcessors();

        CountDownLatch latch = new CountDownLatch(processors);

        List<Callable<CountSplit>> callable = new ArrayList<>();
        for (int i = 0; i < processors; i++) {
            callable.add(new MapReduceAdder(getCounter(), PER_ADDER, latch));
        }

        List<Future<CountSplit>> results = new ArrayList<>(processors);
        ExecutorService service = Executors.newFixedThreadPool(processors);
        System.out.println(String.format("Running \"%1$s\" concurrent trial on %2$d processors", getTrialName(), processors));
        long trialStart = System.currentTimeMillis();
        for (int i = 0; i < processors; i++) {
            results.add(service.submit(callable.get(i)));
        }

        // Use countdown latch to awake on completion
        try {
            latch.await();
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
            ie.printStackTrace();
            System.err.println("Main thread interrupted!");
        }

        long timeConsumed = 0L;
        int actual = 0;
        for (Future<CountSplit> future : results) {
            CountSplit split = future.get();
            actual += split.getCount();
            timeConsumed += split.getTimeConsumed();
        }

        long trialStop = System.currentTimeMillis();
        long elapsedTime = trialStop - trialStart;

        // Check the result
        service.shutdown();

        return new TrialResult(getTrialName(), elapsedTime, timeConsumed, processors * PER_ADDER, actual);
    }

    private Counter getCounter() {
        return new UnsafeCounter();
    }

    private String getTrialName() {
        return "map reduce";
    }
}
