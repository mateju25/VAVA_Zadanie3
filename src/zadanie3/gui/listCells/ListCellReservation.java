package zadanie3.gui.listCells;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import zadanie3.model.Reservation;
import zadanie3.model.Room;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ListCellReservation extends ListCell<Reservation> {


    public Pane pane;
    public Label textCustomer;
    public Label textRoom;
    public Label fromDate;
    public Label toDate;
    public Label textTotalCost;
    private FXMLLoader mLLoader;
    @Override
    protected void updateItem(Reservation emp, boolean empty) {
        super.updateItem(emp, empty);

        if(empty || emp == null) {

            setText(null);
            setGraphic(null);

        } else {

            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/zadanie3/gui/listCells/listCellReservation.fxml"));
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            ListCellReservation controller = mLLoader.getController();
            controller.textCustomer.setText(emp.getResponsibleCust().toString());
            controller.textRoom.setText(emp.getWantedRoom().getIdentifier());
            controller.toDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(emp.getAccomodatedTo()));
            controller.fromDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(emp.getAccomodatedFrom()));
            controller.textTotalCost.setText(String.valueOf(emp.getTotalValue()));


            setText(null);
            setGraphic(controller.pane);
        }

    }
}
