package gui.tasks;

import gui.model.TaskReport;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

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
                LinkedList.class,
                list,
                map,
                fillTime,
                0d, // Sort time = 0
                totalRunTime);
    }
}
