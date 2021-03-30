package zadanie3.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private RoomCategory category = null;
    private String identifier = null;
    private String note = null;
    private List<Image> gallery = new ArrayList<>();

    public Room(RoomCategory category, String identifier) {
        this.category = category;
        this.identifier = identifier;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getNote() {
        return note;
    }

    public List<Image> getGallery() {
        return gallery;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setGallery(List<Image> gallery) {
        this.gallery = gallery;
    }
}
