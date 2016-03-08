package gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

    public MainController() {
    }

    /**
     * Closes the app, when clicking on the "Close" menu item.
     */
    @FXML
    public void onClose() {
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

    /**
     * Invoked when the user clicks on the "View Logs" menu item.
     */
    @FXML
    private void onViewLogs() throws IOException {
        // View the logs file.
        File file = new File(Utils.LOG_FILE_NAME);
        if (file.exists())
            Desktop.getDesktop().open(file);
        else {
            // The log file was not found
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Log file not found.");
            alert.setContentText("Please make sure that you have saved " +
                    "your log file & that it truly exists.");

            alert.showAndWait();
        }
    }

    /**
     * Invoked when the user clicks on the "Source code" menu item.
     */
    @FXML
    private void onViewSource() throws MalformedURLException {
        openWebpage(new URL("https://github.com/DeveloperXY/JavaCollections-Benchmarking"));
    }

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
