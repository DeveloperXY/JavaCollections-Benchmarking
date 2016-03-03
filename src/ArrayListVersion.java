import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
    private static long runTime;
    /**
     * The list that will hold the random integers.
     */
    private static List<Integer> list;

    public static void main(String[] args) {
        getInput();
        runTime = System.nanoTime();

        list = new ArrayList<>();
        System.out.println("First: " + runTime / Math.pow(10, 9));

        getRandom();
        System.out.println("List: " + list);
        System.out.println(String.format("Run time: %d", runTime));
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
        runTime = System.nanoTime() - runTime;
        System.out.println("Second: " + runTime / Math.pow(10, 9));

        while (list.size() < N) {
            int number = ThreadLocalRandom.current()
                    .nextInt(0, M);
            System.out.println(number);
            if (isPrime(number)) {
                list.add(number);
                runTime = System.nanoTime() - runTime;
            }
        }
    }

    /**
     * @param number
     * @return true if @number is a prime one, & false otherwise.
     */
    private static boolean isPrime(int number) {
        if (number == 1)
            return false;

        for (int i = 2; i < Math.sqrt(number); i++)
            if (number % i == 0)
                return false;

        return true;
    }
}
