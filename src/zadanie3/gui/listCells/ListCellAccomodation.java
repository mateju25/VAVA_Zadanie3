package zadanie3.gui.listCells;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import zadanie3.model.Accomodation;
import zadanie3.model.Reservation;
import zadanie3.model.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ListCellAccomodation extends ListCell<Accomodation> {


    public Pane pane;
    public Label textCustomer;
    public Label textRoom;
    public Label fromDate;
    public Label toDate;
    public Label textTotalCost;
    public Label textPayed;
    public TextArea textServices;
    private FXMLLoader mLLoader;
    @Override
    protected void updateItem(Accomodation emp, boolean empty) {
        super.updateItem(emp, empty);

        if(empty || emp == null) {

            setText(null);
            setGraphic(null);

        } else {

            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/zadanie3/gui/listCells/listCellAccomodation.fxml"));
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            ListCellAccomodation controller = mLLoader.getController();
            controller.textCustomer.setText(emp.getResponsibleCust().toString());
            controller.textRoom.setText(emp.getUsedRooms().getIdentifier());
            controller.toDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(emp.getAccomodatedTo()));
            controller.fromDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(emp.getAccomodatedFrom()));
            controller.textTotalCost.setText(String.valueOf(emp.getTotalValue()));
            if (emp.getPaid() != null)
                controller.textPayed.setText("Zaplatené " + (emp.getPaid().isPaidByCard() ? "kartou" : "v hotovosti"));
            else
                controller.textPayed.setText("Nezaplatené");
            controller.textServices.setText("");
            for (Service serv:
                 emp.getUsedServices()) {
                controller.textServices.setText(controller.textServices.getText() + serv.getDescription() + " Využité: " + new SimpleDateFormat("dd.MM.yyyy").format(serv.getUsed()) + "\n" );
            }


            setText(null);
            setGraphic(controller.pane);
        }

    }
}
