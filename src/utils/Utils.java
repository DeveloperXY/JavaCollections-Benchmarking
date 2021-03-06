package utils;

/**
 * Created by Mohammed Aouf ZOUAG on 03/03/2016.
 * <p>
 * A utility class.
 */
public class Utils {

    /**
     * The filename of the logs file of the app.
     */
    public static final String LOG_FILE_NAME = "benchmarking_logs.txt";

    /**
     * @param number
     * @return true if @number is a prime one, & false otherwise.
     */
    public static boolean isPrime(int number) {
        if (number == 1)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++)
            if (number % i == 0)
                return false;

        return true;
    }
}
