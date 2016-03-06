package gui.tasks;

import gui.model.TaskReport;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 * <p>
 * The ArrayList version.
 */
public class ArrayListTask extends BaseTask<List<Integer>>
        implements Callable<TaskReport> {

    public ArrayListTask(int n, int m) {
        super(n, m);
    }

    @Override
    public TaskReport call() throws Exception {
        double fillTime = 0;
        double sortTime = 0;
        double totalRunTime = 0;
        Map<Integer, Double> map = new TreeMap<>();
        List<Integer> list = new ArrayList<>();

        // 1- Calculate the fill time
        fillTime = getFillTime(list, map);

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
