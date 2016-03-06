package gui.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 * <p>
 * An instance of this class represents the collection of results
 * of a certain version task.
 */
public class TaskReport {
    /**
     * The list of the prime, randomly generated numbers by the task.
     */
    private List<Integer> generatedNumbers;

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

    public TaskReport(List<Integer> generatedNumbers,
                      Map<Integer, Double> timingMap,
                      Double fillTime,
                      Double sortTime,
                      Double totalRunTime) {
        this.generatedNumbers = generatedNumbers;
        this.timingMap = timingMap;
        this.fillTime = fillTime;
        this.sortTime = sortTime;
        this.totalRunTime = totalRunTime;
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
