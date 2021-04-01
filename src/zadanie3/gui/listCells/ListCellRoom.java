package zadanie3.gui.listCells;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import zadanie3.model.Room;

import java.io.IOException;

public class ListCellRoom extends ListCell<Room> {

    public Pane pane;
    public Label textName;
    public Label textValue;
    public Label textNote;
    public Label textId;
    public ListView<Image> viewImage;
    public Label textAvailable;
    private FXMLLoader mLLoader;
    @Override
    protected void updateItem(Room emp, boolean empty) {
        super.updateItem(emp, empty);

        if(empty || emp == null) {

            setText(null);
            setGraphic(null);

        } else {

            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/zadanie3/gui/listCells/listCellRoom.fxml"));
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            ListCellRoom controller = mLLoader.getController();
            controller.textName.setText(emp.getCategory().getDescription());
            controller.textId.setText(emp.getIdentifier());
            controller.textValue.setText(String.valueOf(emp.getCategory().getCost()));
            controller.textNote.setText(emp.getNote());
            controller.viewImage.setCellFactory(cell -> { return new ListCell<Image>() {
                    @Override
                    protected void updateItem(Image item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(null);
                            ImageView  imageView = new ImageView();
                            imageView.setFitHeight(170);
                            imageView.setFitWidth(170);
                            imageView.setImage(item);
                            setGraphic(imageView);
                        }
                    }
                };
            });
            controller.viewImage.getItems().setAll(emp.getGallery());
            setText(null);
            setGraphic(controller.pane);
        }

    }
}
