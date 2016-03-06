package gui.windows;

import gui.controllers.CenterController;
import gui.controllers.MainController;
import gui.model.Record;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class MainWindow extends Application {

    /**
     * The primary stage of this window.
     */
    private Stage mPrimaryStage;
    /**
     * The BorderPane assembling the components of the main UI.
     */
    private BorderPane mBorderPane;

    private ObservableList<Record> mRecords;

    @Override
    public void start(Stage stage) throws Exception {
        mPrimaryStage = stage;
        mRecords = FXCollections.observableArrayList();

        initMainWindow();
        addCenterLayout();
        setupStage();
    }

    private void initMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/layout/main_window.fxml"));
        mBorderPane = loader.load();
        MainController mainController = loader.getController();
        mainController.setOwnerStage(mPrimaryStage);
    }

    private void addCenterLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/layout/center_layout.fxml"));

        SplitPane splitPane = loader.load();
        mBorderPane.setCenter(splitPane);

        CenterController centerController = loader.getController();
        centerController.setCenterListener(() -> mRecords);
    }

    private void setupStage() {
        mPrimaryStage.setScene(new Scene(mBorderPane));
        mPrimaryStage.setTitle("Java Collections / Benchmarking");
        mPrimaryStage.show();
    }
}
