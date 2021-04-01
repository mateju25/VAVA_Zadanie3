package zadanie3.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
    private RoomCategory category = null;
    private String identifier = null;
    private String note = null;
    private transient List<Image> gallery = new ArrayList<>();
    private List<String> pathsForImages = new ArrayList<>();
    private boolean isFree = true;

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
        if (gallery == null) {
            gallery = new ArrayList<>();
            for (String path :
                    pathsForImages) {
                try {
                    File file = new File(path);
                    BufferedImage buff = ImageIO.read(file);
                    gallery.add(SwingFXUtils.toFXImage(buff, null));
                } catch (IllegalArgumentException | IOException e) {
                    pathsForImages.remove(path);
                }
            }
        }
        return gallery;
    }

    public List<String> getPathsForImages() {
        return pathsForImages;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setGallery(List<Image> gallery) {
        this.gallery = gallery;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return identifier + " " + category.getDescription() + " Cena: " + String.valueOf(category.getCost());
    }
}
