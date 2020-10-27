package net.thread.classic;

import net.thread.model.Adder;
import net.thread.model.Counter;

import java.util.ArrayList;
import java.util.List;

public class BrokenCounter {
    private static final int PER_ADDER = 1000;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final long CALC_DELAY = MILLISECONDS_PER_SECOND * SECONDS_PER_MINUTE;

    public static void main(String[] args) {
        Counter counter = new Counter();
        int processors = Runtime.getRuntime().availableProcessors();
        List<Thread> threads = new ArrayList<>(processors);
        System.out.println(String.format("Running broken counter on (%1$d) processors", processors));
        for (int i = 0; i < processors; i++) {
            threads.add(new Thread(new Adder(counter, PER_ADDER)));
        }

        for (int i = 0; i < processors; i++) {
            Thread thread = threads.get(i);
            thread.start();
        }

        try {
            Thread.sleep(CALC_DELAY);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
            ie.printStackTrace();
            System.err.println("Main thread interrupted!");
        }

        final int CORRECT_RESULT = processors * PER_ADDER;
        String message;
        if (CORRECT_RESULT == counter.getCount()) {
            message = String.format("Received the correct result! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        } else {
            message = String.format("Received an incorrect result (Which we want ;-P)! (expected: %1$d, actual: %2$d)", CORRECT_RESULT, counter.getCount());
        }
        System.out.println(message);
    }
}
