package gui.controllers;

import gui.model.TaskReport;
import gui.tasks.ArrayListTask;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class CenterController {

    /**
     * The owner stage.
     */
    private Stage mStage;

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

    public CenterController() {
    }

    /**
     * @param ownerStage that this controller manages.
     *                   <p>
     *                   Overloaded constrcutor.
     */
    public CenterController(Stage ownerStage) {
        mStage = ownerStage;
    }

    /**
     * Invoked when the user clicks on the "Run" button.
     */
    @FXML
    private void onStart() throws ExecutionException, InterruptedException {
        statusArea.setText("Executing tasks...");

        if (isInputValid()) {
            // Start the 3 tasks
            ArrayListTask task = new ArrayListTask(N, M);
            FutureTask<TaskReport> futureTask1 = new FutureTask<>(task);

            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.execute(futureTask1);

            TaskReport report1 = futureTask1.get();
            statusArea.setText("ArrayList sequence: " + report1.getGeneratedNumbers());
            executorService.shutdown();
        }
    }

    /**
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
            alert.initOwner(mStage);
            alert.setTitle("Unable to proceed.");
            alert.setHeaderText("Please correct the invalid fields");
            alert.setContentText(dialogMessage);

            alert.showAndWait();

            return false;
        }

        return true;
    }
}
