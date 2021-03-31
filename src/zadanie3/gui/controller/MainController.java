package zadanie3.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends PrimitiveController{

    public DatePicker datePick;
    public Button btnCustomer;

    public void viewCustomers(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/customers.fxml", btnCustomer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewReservations(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/reservations.fxml", btnCustomer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewAccomodations(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/accomodations.fxml", btnCustomer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewRooms(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/rooms.fxml", btnCustomer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewServices(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/services.fxml", btnCustomer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewPayments(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/payments.fxml", btnCustomer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
