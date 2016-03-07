package gui.tasks;

import gui.model.TaskReport;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 * <p>
 * The Set version.
 */
public class SetTask extends BaseTask<Set<Integer>>
        implements Callable<TaskReport> {

    public SetTask(int n, int m) {
        super(n, m);
    }

    @Override
    public TaskReport call() throws Exception {
        double fillTime;
        double sortTime = 0;
        double totalRunTime;
        Map<Integer, Double> map = new TreeMap<>();
        Set<Integer> set = new TreeSet<>();

        // 1- Calculate the fill time
        fillTime = getFillTime(set, map);

        // 2- Calculate the total run time
        totalRunTime = fillTime + sortTime;

        return new TaskReport(
                LocalDateTime.now(),
                TreeSet.class,
                set,
                map,
                fillTime,
                sortTime,
                totalRunTime);
    }

    /**
     * @param set in question
     * @param map        where the timing of each iteration will be saved.
     * @return the time needed to fill the collection.
     */
    public double getFillTime(Set<Integer> set, Map<Integer, Double> map) {
        long timeCounter, fillTime = 0;
        int counter = 1;

        while (counter < N + 1) {
            timeCounter = System.nanoTime();
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            if (Utils.isPrime(number)) {
                set.add(number);
                timeCounter = System.nanoTime() - timeCounter;
                fillTime += timeCounter;
                map.put(counter, timeCounter / Math.pow(10, 9));
            }
            counter++;
        }

        return fillTime / Math.pow(10, 9);
    }
}
