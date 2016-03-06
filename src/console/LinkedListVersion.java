package console;

import utils.Utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Mohammed Aouf ZOUAG on 03/03/2016.
 * <p>
 * The LinkedList version of the program.
 */
public class LinkedListVersion {
    /**
     * The limit number of elements to be contained within the LinkedList.
     */
    private static int N;
    /**
     * The upper bound of the generated numbers' range.
     */
    private static int M;
    /**
     * The total run time of the program.
     */
    private static long totalRunTime;
    /**
     * The linked list that will hold the random integers.
     */
    private static List<Integer> list;

    public static void main(String[] args) {
        getInput();
        list = new LinkedList<>();

        getRandom();
        System.out.println("Linked list elements: " + list + "\n\n");
        System.out.println(String.format("Time elapsed to fill the linked list: %f seconds.",
                totalRunTime / Math.pow(10, 9)));

        System.out.println(String.format("Total run time: %f seconds.\n\n",
                totalRunTime / Math.pow(10, 9)));
    }

    /**
     * Retrieves user input at the beginning of the program.
     */
    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("N: ");
        N = scanner.nextInt();
        System.out.println("M: ");
        M = scanner.nextInt();
    }

    /**
     * Generate the random sequence of numbers.
     */
    public static void getRandom() {
        long time;
        int counter = 1;
        Map<Integer, Double> map = new TreeMap<>();

        while (list.size() < N) {
            time = System.nanoTime();
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            if (Utils.isPrime(number)) {
                // Add & sort
                list.add(number);
                // TODO: good old sorting algorithm
                Collections.sort(list);

                time = System.nanoTime() - time;
                totalRunTime += time;
                map.put(counter, time / Math.pow(10, 9));
            }

            counter++;
        }

        printDashboardMap(map);
    }

    private static void printDashboardMap(Map<Integer, Double> map) {
        System.out.println("\n**************************************");
        System.out.println("Printing the results' map:\n");
        map.forEach((k, v) ->
                System.out.println(
                        String.format("Iteration %d - Time elapsed: %f seconds.",
                                k, v)));
        System.out.println("\n**************************************");
    }
}
