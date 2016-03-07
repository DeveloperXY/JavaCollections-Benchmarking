package gui.tasks;

import gui.model.TaskReport;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 * <p>
 * The LinkedList version.
 */
public class LinkedListTask extends BaseTask<List<Integer>>
        implements Callable<TaskReport> {

    public LinkedListTask(int n, int m) {
        super(n, m);
    }

    @Override
    public TaskReport call() throws Exception {
        double fillTime;
        double totalRunTime;
        Map<Integer, Double> map = new TreeMap<>();
        List<Integer> list = new LinkedList<>();

        // 1- Calculate the fill time
        fillTime = getFillTime(list, map);

        // 2- Calculate the total run time
        totalRunTime = fillTime;

        return new TaskReport(
                LocalDateTime.now(),
                LinkedList.class,
                list,
                map,
                fillTime,
                0d, // Sort time = 0
                totalRunTime);
    }

    /**
     * @param list in question
     * @param map  where the timing of each iteration will be saved.
     * @return the time needed to fill the collection.
     */
    @Override
    public double getFillTime(List<Integer> list, Map<Integer, Double> map) {
        long timeCounter, fillTime = 0;
        int counter = 1;

        while (list.size() < N) {
            timeCounter = System.nanoTime();
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            if (Utils.isPrime(number)) {
                if (list.size() == 0) {
                    list.add(number);
                } else if (list.get(0) > number) {
                    list.add(0, number);
                } else if (list.get(list.size() - 1) < number) {
                    list.add(list.size(), number);
                } else {
                    int i = 0;
                    while (list.get(i) < number) {
                        i++;
                    }
                    list.add(i, number);
                }

                timeCounter = System.nanoTime() - timeCounter;
                fillTime += timeCounter;
                map.put(counter, timeCounter / Math.pow(10, 9));
            }
            counter++;
        }

        return fillTime / Math.pow(10, 9);
    }
}
