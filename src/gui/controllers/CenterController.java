package gui.controllers;

import gui.model.TaskReport;
import gui.tasks.ArrayListTask;
import gui.tasks.LinkedListTask;
import gui.tasks.SetTask;
import gui.windows.MainWindow;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class CenterController {

    private CenterListener mListener;

    /**
     * The minimum capacity of elements to be reached by the collections.
     */
    @FXML
    private TextField nField;
    /**
     * The upper (max) bound of the generated numbers.
     */
    @FXML
    private TextField mField;

    @FXML
    private TextArea statusArea;

    private int N;
    private int M;

    @FXML
    private void initialize() {

    }

    /**
     * Invoked when the user clicks on the "Run" button.
     */
    @FXML
    private void onStart() throws ExecutionException, InterruptedException {
        statusArea.setText("Executing tasks...");

        if (isInputValid()) {
            // Create the 3 tasks
            ArrayListTask arraylistTask = new ArrayListTask(N, M);
            LinkedListTask linkedListTask = new LinkedListTask(N, M);
            SetTask setTask = new SetTask(N, M);

            FutureTask<TaskReport> futureArrayListTask = new FutureTask<>(arraylistTask);
            FutureTask<TaskReport> futureLinkedListTask = new FutureTask<>(linkedListTask);
            FutureTask<TaskReport> futureSetTask = new FutureTask<>(setTask);

            // Execute the tasks
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.execute(futureArrayListTask);
            executorService.execute(futureLinkedListTask);
            executorService.execute(futureSetTask);

            // Get their reports
            TaskReport arraylistReport = futureArrayListTask.get();
            TaskReport linkedlistReport = futureLinkedListTask.get();
            TaskReport setReport = futureSetTask.get();

            // Show the reports
            statusArea.setText(
                    new StringJoiner("\n")
                            .add(arraylistReport.fullReport())
                            .add(TaskReport.taskReportSeparator())
                            .add(linkedlistReport.fullReport())
                            .add(TaskReport.taskReportSeparator())
                            .add(setReport.fullReport())
                            .toString());
            // Shut down the executor
            executorService.shutdown();
        }
    }

    /**
     * @return true if the input is valid, false otherwise.
     *
     * Validates the values of 'N' & 'M'.
     */
    private boolean isInputValid() {
        String dialogMessage = "";
        String nString = nField.getText();
        String mString = mField.getText();

        if ("".equals(nString))
            dialogMessage += "Please specify the minimum number of elements per collection.\n";
        if ("".equals(mString))
            dialogMessage += "Please set the upper bound of the generated numbers.\n";

        if ("".equals(dialogMessage)) {
            try {
                // Try to convert the content of the nField
                N = Integer.parseInt(nString);
            } catch (NumberFormatException e) {
                // Invalid format
                dialogMessage += "N must be an integer.\n";
            }

            try {
                // Try to convert the content of the mField
                M = Integer.parseInt(mString);
            } catch (NumberFormatException e) {
                // Invalid format
                dialogMessage += "M must be an integer.\n";
            }
        }

        if (!"".equals(dialogMessage)) {
            // Something is wrong with the input, show an error dialog.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unable to proceed.");
            alert.setHeaderText("Please correct the invalid fields");
            alert.setContentText(dialogMessage);

            alert.showAndWait();

            return false;
        }

        return true;
    }

    public void setCenterListener(CenterListener listener) {
        mListener = listener;
    }

    public interface CenterListener {
        ObservableMap<String, List<Double>> getRecords();
    }
}
