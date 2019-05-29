package zeiterfassung;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import zeiterfassung.controllers.BaseController;
import zeiterfassung.xml.DataStore;

import java.io.IOException;

public class ZeitErfassung {
    private Stage stage;
    private DataStore store;

    public ZeitErfassung(Stage stage) {
        this.stage = stage;

        // init data store
        this.store = new DataStore();
        this.store.load();

        // load scene
        Parent page = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/zeiterfassung/views/Base.fxml"));
        try {
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseController baseController = loader.getController();
        baseController.setDataStore(this.store);

        Scene scene = new Scene(page);
        scene.getStylesheets().add(getClass().getResource("/zeiterfassung/styles/main.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Zeiterfassung");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/zeiterfassung/icons/clipboard.png")));
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * stop ZeitErfassung
     */
    public void stop() {
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * save currently loaded data store
     */
    public void save() {
        if (this.store != null) {
            this.store.unload();
        }
    }
}
