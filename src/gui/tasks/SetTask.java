package gui.tasks;

import gui.model.TaskReport;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 *
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
                TreeSet.class,
                set,
                map,
                fillTime,
                sortTime,
                totalRunTime);
    }
}
