package gui.model;

import java.util.Collection;
import java.util.Map;
import java.util.StringJoiner;

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

    public TaskReport(Class className,
                      Collection<Integer> generatedNumbers,
                      Map<Integer, Double> timingMap,
                      Double fillTime,
                      Double sortTime,
                      Double totalRunTime) {
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
     * @return a full report of the task results.
     */
    public String fullReport() {
        return new StringJoiner("\n")
                .add("***************************")
                .add(String.format("Class:\t%s", className.getSimpleName()))
                .add("***************************")
                .add("Generated numbers: " + generatedNumbers)
                .add(String.format("Total run time: %f seconds.", totalRunTime))
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
