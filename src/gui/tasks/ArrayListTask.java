package gui.tasks;

import gui.model.TaskReport;
import utils.Utils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 * <p>
 * The ArrayList version.
 */
public class ArrayListTask implements Callable<TaskReport> {

    private int N;
    private int M;

    public ArrayListTask(int n, int m) {
        N = n;
        M = m;
    }

    @Override
    public TaskReport call() throws Exception {
        long timeCounter, tmp = 0;
        int counter = 1;
        double fillTime = 0;
        double sortTime = 0;
        double totalRunTime = 0;
        Map<Integer, Double> map = new TreeMap<>();
        List<Integer> list = new ArrayList<>();

        // 1- Calculate the fill time
        while (list.size() < N) {
            timeCounter = System.nanoTime();
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            if (Utils.isPrime(number)) {
                list.add(number);
                timeCounter = System.nanoTime() - timeCounter;
                tmp += timeCounter;
                map.put(counter, timeCounter / Math.pow(10, 9));
            }
            counter++;
        }

        fillTime = tmp / Math.pow(10, 9);

        // 2- Calculate the sort time
        Function<List<Integer>, Long> func =
                l -> {
                    long time = System.nanoTime();
                    Collections.sort(l);
                    return System.nanoTime() - time;
                };
        sortTime = func.apply(list) / Math.pow(10, 9);

        // 3- Calculate the total run time
        totalRunTime = fillTime + sortTime;

        return new TaskReport(
                list,
                map,
                fillTime,
                sortTime,
                totalRunTime);
    }
}
