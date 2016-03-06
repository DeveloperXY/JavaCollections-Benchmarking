package gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class MainController {

    /**
     * Closes the app, when clicking on the "Close" menu item.
     */
    @FXML
    private void onClose() {
        Platform.exit();
    }
}
