package gui.controllers;

import gui.model.Record;
import gui.model.TaskReport;
import gui.tasks.ArrayListTask;
import gui.tasks.LinkedListTask;
import gui.tasks.SetTask;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class CenterController {

    /**
     * A MainWindow listener on this controller.
     */
    private CenterListener mListener;

    /**
     * The main TableView of the app.
     */
    @FXML
    private TableView<Record> recordTable;
    /**
     * The ArrayList column.
     */
    @FXML
    private TableColumn<Record, String> arraylistColumn;
    /**
     * The LinkedList column.
     */
    @FXML
    private TableColumn<Record, String> linkedlistColumn;
    /**
     * The Set column.
     */
    @FXML
    private TableColumn<Record, String> setColumn;

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

    /**
     * The status area TextArea, down to the right.
     */
    @FXML
    private TextArea statusArea;

    private int N;
    private int M;

    public CenterController() {
    }

    @FXML
    private void initialize() {
        arraylistColumn.setCellValueFactory(cellData ->
                cellData.getValue().arrayListProperty());
        linkedlistColumn.setCellValueFactory(cellData ->
                cellData.getValue().linkedlistProperty());
        setColumn.setCellValueFactory(cellData ->
                cellData.getValue().setProperty());
    }

    /**
     * Invoked when the user clicks on the "Run" button.
     */
    @FXML
    private void onStart() {
        statusArea.setText("Executing tasks...");
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        if (isInputValid()) {

            try {
                // Create the 3 tasks
                ArrayListTask arraylistTask = new ArrayListTask(N, M);
                LinkedListTask linkedListTask = new LinkedListTask(N, M);
                SetTask setTask = new SetTask(N, M);

                FutureTask<TaskReport> futureArrayListTask = new FutureTask<>(arraylistTask);
                FutureTask<TaskReport> futureLinkedListTask = new FutureTask<>(linkedListTask);
                FutureTask<TaskReport> futureSetTask = new FutureTask<>(setTask);

                // Execute the tasks
                executorService.execute(futureArrayListTask);
                executorService.execute(futureLinkedListTask);
                executorService.execute(futureSetTask);

                // Get their reports
                TaskReport arraylistReport = futureArrayListTask.get();
                System.out.println("OK1");
                TaskReport linkedlistReport = futureLinkedListTask.get();
                System.out.println("OK2");
                TaskReport setReport = futureSetTask.get();
                System.out.println("OK3");

                // Add record to the TableView
                Record record = new Record(arraylistReport.getTotalRunTime(),
                        linkedlistReport.getTotalRunTime(),
                        setReport.getTotalRunTime());
                mListener.getRecords().add(record);

                // Scroll down to the last item after inserting it
                recordTable.scrollTo(recordTable.getItems().size());

                // Show the reports
                statusArea.setText(
                        new StringJoiner("\n")
                                .add(arraylistReport.miniReport())
                                .add(TaskReport.taskReportSeparator())
                                .add(linkedlistReport.miniReport())
                                .add(TaskReport.taskReportSeparator())
                                .add(setReport.miniReport())
                                .toString());

                logTheReports(arraylistReport, linkedlistReport, setReport);

            } catch (InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
            }
            finally {
                executorService.shutdown();
            }
        }
    }

    /**
     * @param reports to be logged
     *                <p>
     *                Logs the passed-in reports to a log text file.
     */
    private void logTheReports(TaskReport... reports) throws IOException {
        Path file = Paths.get(Utils.LOG_FILE_NAME);
        Files.write(file,
                Arrays.asList(reports[0], reports[1], reports[2])
                        .stream()
                        .map(TaskReport::fullReport)
                        .collect(Collectors.toList()),
                Charset.forName("UTF-8"));
    }

    /**
     * @return true if the input is valid, false otherwise.
     * <p>
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

        if (M == 0)
            dialogMessage += "The value of 'M' should be different than 0.";

        if (!"".equals(dialogMessage)) {
            // Something is wrong with the input, show an error dialog.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unable to proceed.");
            alert.setHeaderText("Please correct the invalid fields");
            alert.setContentText(dialogMessage);

            alert.showAndWait();
            statusArea.setText("Ready.");
            return false;
        }

        return true;
    }

    public void setCenterListener(CenterListener listener) {
        mListener = listener;
        recordTable.setItems(mListener.getRecords());
    }

    @FunctionalInterface
    public interface CenterListener {
        ObservableList<Record> getRecords();
    }
}
