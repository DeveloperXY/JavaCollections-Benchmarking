package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void onStart() {
        validateInput();
    }

    /**
     * Validates the values of 'N' & 'M'.
     */
    private void validateInput() {
        String dialogMessage = "";
        String nString = nField.getText();
        String mString = mField.getText();

        int n; // The integer value of 'N'
        int m; // The integer value of 'M'
        n = m = 0;

        if ("".equals(nString))
            dialogMessage += "Please specify the minimum number of elements per collection.\n";
        if ("".equals(mString))
            dialogMessage += "Please set the upper bound of the generated numbers.\n";

        if ("".equals(dialogMessage)) {
            try {
                // Try to convert the content of the nField
                n = Integer.parseInt(nString);
            } catch (NumberFormatException e) {
                // Invalid format
                dialogMessage += "N must be an integer.\n";
            }

            try {
                // Try to convert the content of the mField
                m = Integer.parseInt(mString);
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
        }
    }
}
