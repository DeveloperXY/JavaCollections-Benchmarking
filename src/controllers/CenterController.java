package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class CenterController {

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
     * Invoked when the user clicks on the "Run" button.
     */
    @FXML
    private void onStart() {
        System.out.println("onStart");
    }
}
