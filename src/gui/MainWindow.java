package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class MainWindow extends Application {

    private Stage mPrimaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        mPrimaryStage = stage;
        initMainWindow();
    }

    private void initMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/layout/main_window.fxml"));
        BorderPane root = loader.load();

        mPrimaryStage.setScene(new Scene(root));
        mPrimaryStage.setTitle("Java Collections / Benchmarking");
        mPrimaryStage.show();
    }
}
