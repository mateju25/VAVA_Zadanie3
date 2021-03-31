package zadanie3.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import zadanie3.model.Database;
import zadanie3.model.RoomCategory;

public class NewRoomCategoryController extends PrimitiveController{
    public TextField textDescription;
    public TextField textValue;
    public Button btnSave;

    public void save(ActionEvent actionEvent) {
        if (!(textDescription.getText().isEmpty() && textValue.getText().isEmpty())) {
            Database.getInstance().getListOfRoomCategories().add(new RoomCategory(textDescription.getText(), Double.parseDouble(textValue.getText())));
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }
    }
}
