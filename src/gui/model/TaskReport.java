package gui.model;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 * <p>
 * An instance of this class represents the collection of results
 * of a certain version task.
 */
public class TaskReport {
    /**
     * The Collection of the prime, randomly generated numbers by the task.
     */
    private Collection<Integer> generatedNumbers;

    /**
     * A map containing the timing of each "successful" iteration.
     * -- A successful iteration is an iteration where the generated number
     * is a prime one.
     */
    private Map<Integer, Double> timingMap;


    /**
     * The duration of time that was needed to fill the collection that
     * the task uses.
     */
    private Double fillTime;

    /**
     * The duration of time that was needed to sort the collection that the task uses.
     * Could be @null if the collection wasn't sorted.
     */
    private Double sortTime;

    /**
     * The total run time of the task.
     * (Fill + Sort(if required) + Add to collection)
     */
    private Double totalRunTime;

    private Class className;
    /**
     * The time this report was made.
     */
    private LocalDateTime time;

    public TaskReport(LocalDateTime time,
                      Class className,
                      Collection<Integer> generatedNumbers,
                      Map<Integer, Double> timingMap,
                      Double fillTime,
                      Double sortTime,
                      Double totalRunTime) {
        this.time = time;
        this.className = className;
        this.generatedNumbers = generatedNumbers;
        this.timingMap = timingMap;
        this.fillTime = fillTime;
        this.sortTime = sortTime;
        this.totalRunTime = totalRunTime;
    }

    public Collection<Integer> getGeneratedNumbers() {
        return generatedNumbers;
    }

    public Map<Integer, Double> getTimingMap() {
        return timingMap;
    }

    public Double getFillTime() {
        return fillTime;
    }

    public Double getSortTime() {
        return sortTime;
    }

    public Double getTotalRunTime() {
        return totalRunTime;
    }

    /**
     * @return a mini report of the task results, to be shown within the app.
     */
    public String miniReport() {
        return new StringJoiner("\n")
                .add("***************************")
                .add(String.format("Class:\t%s", className.getSimpleName()))
                .add("***************************")
                .add("Generated numbers: " + generatedNumbers)
                .add(String.format("Total run time: %f seconds.", totalRunTime))
                .toString();
    }

    /**
     * @return a full report of this task describing all actions, all timings.
     */
    public String fullReport() {
        String reportTime = time.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new StringJoiner(System.lineSeparator())
                .add(Stream.generate(() -> "-") // -----------------------
                        .limit(reportTime.length())
                        .collect(Collectors.joining("")))
                .add(reportTime) // The report's date
                .add(Stream.generate(() -> "-") // ----------------------
                        .limit(reportTime.length())
                        .collect(Collectors.joining("")))
                .add(String.format("Class :\t<%s>", className.getSimpleName()))
                .add("Generated numbers: " + generatedNumbers)
                .add(String.format("Fill time: %f seconds.", fillTime))
                .add(String.format("Sort time: %f seconds.", sortTime))
                .add(String.format("Total run time: %f seconds.", totalRunTime))
                .add(taskReportSeparator())
                .toString();
    }

    /**
     * @return a string separator between task reports.
     */
    public static String taskReportSeparator() {
        return "\n\n\n";
    }

    @Override
    public String toString() {
        return "TaskReport{" +
                "generatedNumbers=" + generatedNumbers +
                ", timingMap=" + timingMap +
                ", fillTime=" + fillTime +
                ", sortTime=" + sortTime +
                ", totalRunTime=" + totalRunTime +
                '}';
    }
}
