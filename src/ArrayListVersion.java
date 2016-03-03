import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * Created by Mohammed Aouf ZOUAG on 03/03/2016.
 */
public class ArrayListVersion {
    /**
     * The limit number of elements to be contained within the List.
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
     * The list that will hold the random integers.
     */
    private static List<Integer> list;

    public static void main(String[] args) {
        getInput();
        list = new ArrayList<>();

        getRandom();
        System.out.println("List elements: " + list + "\n\n");
        System.out.println(String.format("Time elapsed to fill the list: %f seconds.",
                totalRunTime / Math.pow(10, 9)));

        Function<List<Integer>, Long> func =
                list -> {
                    long time = System.nanoTime();
                    Collections.sort(list);
                    return System.nanoTime() - time;
                };

        long time = func.apply(list);
        System.out.println(String.format("Time elapsed to sort the list: %f seconds.",
                time / Math.pow(10, 9)));
        totalRunTime += time;

        System.out.println(String.format("Total run time: %f seconds.\n\n",
                totalRunTime / Math.pow(10, 9)));

        System.out.println("Sorted list: " + list);
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
            // 1- Start
            time = System.nanoTime();
            // 2- Generate
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            // 3- Check if prime
            if (isPrime(number)) {
                // 4- Add
                list.add(number);
                // 5- Finish
                time = System.nanoTime() - time;
                totalRunTime += time;
                // Save iteration count & time elapsed to map
                map.put(counter, time / Math.pow(10, 9));
            }

            counter++;
        }

        System.out.println("\n**************************************");
        System.out.println("Printing the results' map:\n");
        map.forEach((k, v) ->
                System.out.println(String.format("Iteration %d - Time elapsed: %f seconds.", k, v)));
        System.out.println("\n**************************************");
    }

    /**
     * @param number
     * @return true if @number is a prime one, & false otherwise.
     */
    private static boolean isPrime(int number) {
        if (number == 1)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++)
            if (number % i == 0)
                return false;

        return true;
    }
}
