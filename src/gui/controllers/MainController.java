package gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class MainController {

    /**
     * The owner stage.
     */
    private Stage mStage;

    /**
     * The menu item in charge of handling of toggling the fullscreen mode.
     */
    @FXML
    private MenuItem fullscreenBtn;

    public void setOwnerStage(Stage stage) {
        mStage = stage;
    }

    /**
     * Closes the app, when clicking on the "Close" menu item.
     */
    @FXML
    private void onClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(mStage);
        alert.setTitle("Quit");
        alert.setHeaderText("Are you sure that you want to close the app ?");

        Optional<ButtonType> type = alert.showAndWait();
        if (type.get().equals(ButtonType.OK))
            Platform.exit();
    }

    /**
     * Toggles the Fullscreen mode of the main window.
     */
    @FXML
    private void onFullscreen() {
        // Toggle fullscreen mode
        mStage.setFullScreen(!mStage.isFullScreen());
        // Set the text of the Fullscreen menu item accordingly
        fullscreenBtn.setText(mStage.isFullScreen() ?
                "Exit Fullscreen mode" : "Enter Fullscreen mode");
    }
}
