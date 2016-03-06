package gui.tasks;

import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

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
        return null;
    }

    /**
     * Generate the random sequence of numbers.
     */
    /*public void getRandom() {
        long time;
        int counter = 1;
        Map<Integer, Double> map = new TreeMap<>();
        List<Integer> generated_list = new ArrayList<>();

        while (map.size() < N) {
            time = System.nanoTime();
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            if (Utils.isPrime(number)) {
                generated_list.add(number);
                time = System.nanoTime() - time;
                totalRunTime += time;
                map.put(counter, time / Math.pow(10, 9));
            }

            counter++;
        }
    }*/
}
