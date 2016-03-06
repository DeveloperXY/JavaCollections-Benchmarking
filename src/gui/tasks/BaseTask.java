package gui.tasks;

import utils.Utils;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class BaseTask<T extends Collection<Integer>> {
    protected int N;
    protected int M;

    public BaseTask(int n, int m) {
        N = n;
        M = m;
    }

    public double getFillTime(T collection, Map<Integer, Double> map) {
        long timeCounter, fillTime = 0;
        int counter = 1;

        while (collection.size() < N) {
            timeCounter = System.nanoTime();
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            if (Utils.isPrime(number)) {
                collection.add(number);
                timeCounter = System.nanoTime() - timeCounter;
                fillTime += timeCounter;
                map.put(counter, timeCounter / Math.pow(10, 9));
            }
            counter++;
        }

        return fillTime / Math.pow(10, 9);
    }
}
