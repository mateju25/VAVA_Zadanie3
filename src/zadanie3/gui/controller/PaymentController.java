package zadanie3.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import zadanie3.model.Accomodation;
import zadanie3.model.Customer;
import zadanie3.model.Database;
import zadanie3.model.Payment;

import java.io.IOException;
import java.util.stream.Collectors;

public class PaymentController extends PrimitiveController{
    public Button btnCancel;
    public ListView<Payment> viewPayments;
    public Label warning;
    public CheckBox checkCard;
    public ChoiceBox<Accomodation> choiceAccommodation;

    public void initialize() {
        viewPayments.getItems().setAll(Database.getInstance().getListOfPayments());
        choiceAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations().stream().filter(accomodation -> accomodation.getPaid()==null).collect(Collectors.toList()));
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/main.fxml", btnCancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPayment(ActionEvent actionEvent) {
        if (choiceAccommodation.getSelectionModel().getSelectedItem() == null) {
            warning.setText("Vyplň všetky polia!");
        } else {
            warning.setText("");
            Payment newPayment = new Payment(Database.getInstance().getNow(), checkCard.isSelected());
            Database.getInstance().getListOfPayments().add(newPayment);
            choiceAccommodation.getSelectionModel().getSelectedItem().setPaid(newPayment);
            newPayment.refreshDescription();
            viewPayments.getItems().setAll(Database.getInstance().getListOfPayments());
            choiceAccommodation.getItems().setAll(Database.getInstance().getListOfAccomodations().stream().filter(accomodation -> accomodation.getPaid()==null).collect(Collectors.toList()));
        }
    }

    public void removePayment(ActionEvent actionEvent) {
        if (viewPayments.getSelectionModel().getSelectedItem() != null)
            Database.getInstance().getListOfPayments().remove(viewPayments.getSelectionModel().getSelectedItem());
        viewPayments.getItems().setAll(Database.getInstance().getListOfPayments());
    }
}
