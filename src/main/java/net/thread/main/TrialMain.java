package net.thread.main;

import net.thread.concurrent.AtomicCounterTrial;
import net.thread.concurrent.BasicConcurrentTrialTemplate;
import net.thread.concurrent.MapReduceTrialTemplate;
import net.thread.locking.LockPerformAdderTrial;
import net.thread.locking.UnsafeTrial;
import net.thread.locking.LockIncrementAdderTrial;
import net.thread.locking.LockCounterTrial;
import net.thread.model.TrialResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TrialMain {
    public static void main(String[] args) throws Exception {
        List<Callable<TrialResult>> trials = new ArrayList<>();
        trials.add(new UnsafeTrial());
        trials.add(new LockCounterTrial());
        trials.add(new LockPerformAdderTrial());
        trials.add(new LockIncrementAdderTrial());
        trials.add(new BasicConcurrentTrialTemplate());
        trials.add(new MapReduceTrialTemplate());
        trials.add(new AtomicCounterTrial());

        List<TrialResult> results = new ArrayList<>();
        for (Callable<TrialResult> trial : trials) {
            TrialResult result = trial.call();
            results.add(result);
        }

        System.out.println("| Trial                | Elapsed time/ms | Processor time/ms | Error rate/pc |");
        for (TrialResult result : results) {
            System.out.println(String.format("| %1$33s | %2$15d | %3$17d |   %4$4d   |", result.getTrialName(), result.getElapsedTime(), result.getTimeConsumed(), result.getExpected() ));
        }
    }
}
