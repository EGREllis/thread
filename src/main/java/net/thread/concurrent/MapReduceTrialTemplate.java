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
        for (int i = 0; i < results.size(); i++) {
            CountSplit split = results.get(i).get();
            actual += split.getCount();
            timeConsumed += split.getTimeConsumed();
        }

        long trialStop = System.currentTimeMillis();
        long elapsedTime = trialStop - trialStart;

        // Check the result
        final int CORRECT_RESULT = processors * PER_ADDER;
        String message = getMessage(CORRECT_RESULT, actual);
        System.out.println(message);

        service.shutdown();

        return new TrialResult(getTrialName(), elapsedTime, timeConsumed, CORRECT_RESULT, actual);
    }

    private Counter getCounter() {
        return new UnsafeCounter();
    }

    private String getTrialName() {
        return "map reduce";
    }

    protected String getMatchMessage(int expected, int actual) {
        return String.format("Received the correct result! (Which we expect)! (expected: %1$d, actual: %2$d)", expected, actual);
    }

    protected String getMisMatchMessage(int expected, int actual) {
        return String.format("Received an incorrect result! (expected: %1$d, actual: %2$d)", expected, actual);
    }

    private String getMessage(int CORRECT_RESULT, int actual) {
        String message;
        if(CORRECT_RESULT == actual) {
            message = getMatchMessage(CORRECT_RESULT, actual);
        } else {
            message = getMisMatchMessage(CORRECT_RESULT, actual);
        }
        return message;
    }
}
