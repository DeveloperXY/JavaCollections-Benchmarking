package gui.tasks;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 *
 * The ArrayList version.
 */
public class ArrayListTask implements Callable<Map<String, Double>> {

    private int N;
    private int M;
    private long totalRunTime;

    public ArrayListTask(int n, int m) {
        N = n;
        M = m;
    }

    @Override
    public Map<String, Double> call() throws Exception {

    }
}
