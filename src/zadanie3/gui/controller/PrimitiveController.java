package zadanie3.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class PrimitiveController {
    protected void newWindow(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage window = new Stage();
        window.setResizable(false);
        window.getIcons().add(new Image(getClass().getResourceAsStream("/zadanie3/gui/resources/icon.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.showAndWait();
    }
    protected void switchWindow(String path, Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(scene);

    }
}
