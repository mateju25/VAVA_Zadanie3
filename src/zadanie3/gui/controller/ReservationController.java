package zadanie3.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import zadanie3.gui.listCells.ListCellReservation;
import zadanie3.gui.listCells.ListCellRoom;
import zadanie3.model.Customer;
import zadanie3.model.Database;
import zadanie3.model.Reservation;
import zadanie3.model.Room;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

public class ReservationController extends PrimitiveController{
    public Button btnCancel;
    public ListView<Reservation> viewReservations;
    public Label warning;
    public ChoiceBox<Room> choiceRoom;
    public ChoiceBox<Customer> choiceCustomer;
    public DatePicker fromDate;
    public DatePicker toDate;

    public void initialize() {
        viewReservations.setCellFactory(param -> new ListCellReservation());
        viewReservations.getItems().setAll(Database.getInstance().getListOfReservations());
        choiceRoom.getItems().setAll(Database.getInstance().getListOfRooms(Database.getInstance().getNow(), Database.getInstance().getNow()));
        choiceCustomer.getItems().addAll(Database.getInstance().getListOfCustomers());
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/main.fxml", btnCancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReservation(ActionEvent actionEvent) {
        if (choiceCustomer.getSelectionModel().getSelectedItem() == null ||
                choiceRoom.getSelectionModel().getSelectedItem() == null ||
                fromDate.getValue() == null || toDate.getValue() == null) {
            warning.setText("Vyplň všetky polia!");
        } else {
            warning.setText("");
            choiceRoom.getSelectionModel().getSelectedItem().setFree(false);
            Reservation newReserv = new Reservation(choiceCustomer.getSelectionModel().getSelectedItem(),
                    choiceRoom.getSelectionModel().getSelectedItem(),
                    Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            Database.getInstance().getListOfReservations().add(newReserv);
            viewReservations.getItems().setAll(Database.getInstance().getListOfReservations());
            choiceRoom.getItems().setAll(Database.getInstance().getListOfRooms(Database.getInstance().getNow(), Database.getInstance().getNow()));
            choiceCustomer.getSelectionModel().clearSelection();
        }
    }

    public void removeReservation(ActionEvent actionEvent) {
        if (viewReservations.getSelectionModel().getSelectedItem() != null) {
            viewReservations.getSelectionModel().getSelectedItem().getWantedRoom().setFree(true);
            Database.getInstance().getListOfReservations().remove(viewReservations.getSelectionModel().getSelectedItem());
        }
        viewReservations.getItems().setAll(Database.getInstance().getListOfReservations());
    }

    public void changeDate(ActionEvent actionEvent) {
        Date from = null;
        Date to = null;
        if (fromDate.getValue() != null)
            from = Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        else
            from = Database.getInstance().getNow();

        if (toDate.getValue() != null)
            to = Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        else
            to = Database.getInstance().getNow();
        choiceRoom.getItems().setAll(Database.getInstance().getListOfRooms(from, to));
    }
}
