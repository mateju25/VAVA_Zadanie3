package zadanie3.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import zadanie3.model.Customer;
import zadanie3.model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController extends PrimitiveController {
    public ListView<Customer> viewCustomers;
    public Button btnCancel;
    public TextField textName;
    public TextField textNumber;
    public Label warning;

    public void initialize() {
        viewCustomers.getItems().setAll(Database.getInstance().getListOfCustomers());
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/main.fxml", btnCancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(ActionEvent actionEvent) {
        if (textName.getText().isEmpty() || textNumber.getText().isEmpty()) {
            warning.setText("Vyplň všetky polia!");
        } else {
            warning.setText("");
            if (Database.getInstance().getListOfCustomers().stream().anyMatch(cust -> cust.getName().equals(textName.getText()))) {
                warning.setText("Zákazník už existuje!");
                return;
            }

            Database.getInstance().getListOfCustomers().add(new Customer(textName.getText(), textNumber.getText()));
            viewCustomers.getItems().setAll(Database.getInstance().getListOfCustomers());
        }
    }

    public void removeCustomer(ActionEvent actionEvent) {
        if (viewCustomers.getSelectionModel().getSelectedItem() != null)
            Database.getInstance().getListOfCustomers().remove(viewCustomers.getSelectionModel().getSelectedItem());
        viewCustomers.getItems().setAll(Database.getInstance().getListOfCustomers());
    }
}
