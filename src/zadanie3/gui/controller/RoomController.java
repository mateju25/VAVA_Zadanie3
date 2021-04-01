package zadanie3.gui.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import zadanie3.gui.listCells.ListCellRoom;
import zadanie3.model.Customer;
import zadanie3.model.Database;
import zadanie3.model.Room;
import zadanie3.model.RoomCategory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomController extends PrimitiveController{
    public Button btnCancel;
    public TextField textId;
    public TextField textNote;
    public Label warning;
    public ChoiceBox<RoomCategory> choiceCategory;
    public ListView<Room> viewRooms;

    private List<Image> images = new ArrayList<>();

    public void initialize() {
        viewRooms.setCellFactory(param -> new ListCellRoom());
        viewRooms.getItems().setAll(Database.getInstance().getListOfRooms());
        choiceCategory.getItems().addAll(Database.getInstance().getListOfRoomCategories());
    }

    public void loadImages(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(viewRooms.getScene().getWindow());
        if (selectedFile != null) {
            BufferedImage buff = ImageIO.read(selectedFile);
            if (viewRooms.getSelectionModel().getSelectedItem() == null)
                images.add(SwingFXUtils.toFXImage(buff, null));
            else
                viewRooms.getSelectionModel().getSelectedItem().getGallery().add(SwingFXUtils.toFXImage(buff, null));
        }
        viewRooms.getItems().setAll(Database.getInstance().getListOfRooms());
    }

    public void removeRoom(ActionEvent actionEvent) {
        if (viewRooms.getSelectionModel().getSelectedItem() != null)
            Database.getInstance().getListOfRooms().remove(viewRooms.getSelectionModel().getSelectedItem().getIdentifier());
        viewRooms.getItems().setAll(Database.getInstance().getListOfRooms());
    }

    public void addRoom(ActionEvent actionEvent) {
        if (textId.getText().isEmpty() || choiceCategory.getSelectionModel().getSelectedItem() == null) {
            warning.setText("Vyplň všetky polia!");
        } else {
            warning.setText("");
            if (Database.getInstance().getListOfRooms().stream().anyMatch(room -> room.getIdentifier().equals(textId.getText()))) {
                warning.setText("Izba už existuje!");
                return;
            }

            Room newRoom = new Room(choiceCategory.getSelectionModel().getSelectedItem(), textId.getText());
            if (!textNote.getText().isEmpty())
                newRoom.setNote(textNote.getText());

            if (images.size() != 0)
                newRoom.getGallery().addAll(images);

            Database.getInstance().getListOfRooms().add(newRoom);

            viewRooms.getItems().setAll(Database.getInstance().getListOfRooms());
        }
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/main.fxml", btnCancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(ActionEvent actionEvent) {
        try {
            newWindow("/zadanie3/gui/views/newRoomCategory.fxml");
            choiceCategory.getItems().setAll(Database.getInstance().getListOfRoomCategories());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newNote(ActionEvent actionEvent) {
        if (viewRooms.getSelectionModel().getSelectedItem() != null)
            viewRooms.getSelectionModel().getSelectedItem().setNote(textNote.getText());
        viewRooms.getItems().setAll(Database.getInstance().getListOfRooms());
    }
}
