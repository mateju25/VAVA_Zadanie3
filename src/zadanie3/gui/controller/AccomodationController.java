package zadanie3.gui.controller;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import zadanie3.gui.listCells.ListCellAccomodation;
import zadanie3.gui.listCells.ListCellReservation;
import zadanie3.model.*;

import javax.xml.soap.Text;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AccomodationController extends PrimitiveController{
    public Button btnCancel;
    public ListView<Accomodation> viewAccommodation;
    public Label warning;
    public ChoiceBox<Room> choiceRoom;
    public ChoiceBox<Customer> choiceCustomer;
    public DatePicker fromDate;
    public DatePicker toDate;
    public ChoiceBox<Reservation> choiceReservation;
    public ChoiceBox<Service> choiceServices;
    public CheckBox checkFilter;
    public CheckBox checkFilterCustomer;
    public CheckBox checkFilterRoom;

    private DateCell iniCell=null;
    private DateCell endCell=null;


    public void initialize() {
        viewAccommodation.setCellFactory(param -> new ListCellAccomodation());
        viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
        choiceRoom.getItems().setAll(Database.getInstance().getListOfRooms(Database.getInstance().getNow(), Database.getInstance().getNow()));
        choiceCustomer.getItems().addAll(Database.getInstance().getListOfCustomers());
        choiceReservation.getItems().setAll(Database.getInstance().getListOfReservations());
        choiceServices.getItems().setAll(Database.getInstance().getListOfServices());
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/main.fxml", btnCancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAccomodation(ActionEvent actionEvent) {
        if (choiceReservation.getSelectionModel().getSelectedItem() == null) {
            if (choiceCustomer.getSelectionModel().getSelectedItem() == null ||
                    choiceRoom.getSelectionModel().getSelectedItem() == null ||
                    fromDate.getValue() == null || toDate.getValue() == null) {
                warning.setText("Vyplň všetky polia!");
            } else {
                warning.setText("");
                choiceRoom.getSelectionModel().getSelectedItem().setFree(false);
                Accomodation newAccomo = new Accomodation(choiceCustomer.getSelectionModel().getSelectedItem(),
                        choiceRoom.getSelectionModel().getSelectedItem(),
                        Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

                Database.getInstance().getListOfAccomodations().add(newAccomo);
                viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
                choiceRoom.getItems().setAll(Database.getInstance().getListOfRooms(Database.getInstance().getNow(), Database.getInstance().getNow()));
                choiceCustomer.getSelectionModel().clearSelection();
            }
        } else {
            Reservation reserv = choiceReservation.getSelectionModel().getSelectedItem();
            Accomodation newAccomo = new Accomodation(reserv.getResponsibleCust(),
                    reserv.getWantedRoom(),
                    reserv.getAccomodatedFrom(),
                   reserv.getAccomodatedTo());

            Database.getInstance().getListOfAccomodations().add(newAccomo);
            Database.getInstance().getListOfReservations().remove(reserv);
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
            choiceRoom.getItems().setAll(Database.getInstance().getListOfRooms(Database.getInstance().getNow(), Database.getInstance().getNow()));
            choiceCustomer.getSelectionModel().clearSelection();
        }
    }

    public void removeAccomodation(ActionEvent actionEvent) {
        if (viewAccommodation.getSelectionModel().getSelectedItem() != null) {
            viewAccommodation.getSelectionModel().getSelectedItem().getUsedRooms().setFree(true);
            Database.getInstance().getListOfAccomodations().remove(viewAccommodation.getSelectionModel().getSelectedItem());

        }
        viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
        choiceReservation.getItems().setAll(Database.getInstance().getListOfReservations());
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

    public void filterAccomodations(ActionEvent actionEvent) {
        if (checkFilter.isSelected())
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations().stream()
                    .filter(accomodation -> accomodation.getAccomodatedTo().before(Database.getInstance().getNow()))
            .filter(accomodation -> accomodation.getPaid() == null).collect(Collectors.toList()));
        else
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
    }

    public void filterAccomodationsCustomer(ActionEvent actionEvent) {
        if (checkFilterCustomer.isSelected() && choiceCustomer.getSelectionModel().getSelectedItem() != null)
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations().stream()
                    .filter(accomodation -> accomodation.getResponsibleCust() == choiceCustomer.getSelectionModel().getSelectedItem())
                    .collect(Collectors.toList()));
        else
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
    }

    public void filterAccomodationsRoom(ActionEvent actionEvent) {
        if (checkFilterRoom.isSelected() && choiceRoom.getSelectionModel().getSelectedItem() != null)
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations().stream()
                    .filter(accomodation -> accomodation.getUsedRooms() == choiceRoom.getSelectionModel().getSelectedItem())
                    .collect(Collectors.toList()));
        else
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
    }

    public void addService(ActionEvent actionEvent) {
        if (viewAccommodation.getSelectionModel().getSelectedItem() != null && choiceServices.getSelectionModel().getSelectedItem() != null) {
            Service newServ = new Service(choiceServices.getSelectionModel().getSelectedItem().getDescription(), choiceServices.getSelectionModel().getSelectedItem().getCost());
            newServ.setUsed(Database.getInstance().getNow());
            viewAccommodation.getSelectionModel().getSelectedItem().getUsedServices().add(newServ);
            viewAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations());
        }
    }
}
