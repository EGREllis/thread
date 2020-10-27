package net.thread.classic;

import net.thread.model.TrialResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BrokenCounter {
    public static void main(String[] args) throws Exception {
        List<Callable<TrialResult>> trials = new ArrayList<>();
        trials.add(new BrokenTrial());
        trials.add(new LockTrial());

        for (Callable<TrialResult> trial : trials) {
            TrialResult result = trial.call();
            System.out.println(result);
        }
    }
}
