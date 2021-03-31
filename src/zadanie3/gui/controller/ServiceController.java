package zadanie3.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import zadanie3.model.Customer;
import zadanie3.model.Database;
import zadanie3.model.Service;

import java.io.IOException;

public class ServiceController extends PrimitiveController{
    public Button btnCancel;
    public ListView<Service> viewServices;
    public TextField textName;
    public TextField textCost;
    public Label warning;

    public void initialize() {
        viewServices.getItems().setAll(Database.getInstance().getListOfServices());
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            switchWindow("/zadanie3/gui/views/main.fxml", btnCancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addService(ActionEvent actionEvent) {
        if (textName.getText().isEmpty() || textCost.getText().isEmpty()) {
            warning.setText("Vyplň všetky polia!");
        } else {
            warning.setText("");
            if (Database.getInstance().getListOfServices().stream().anyMatch(cust -> cust.getDescription().equals(textName.getText()))) {
                warning.setText("Služba už existuje!");
                return;
            }

            Database.getInstance().getListOfServices().add(new Service(textName.getText(), Double.parseDouble(textCost.getText())));
            viewServices.getItems().setAll(Database.getInstance().getListOfServices());
        }
    }

    public void removeService(ActionEvent actionEvent) {
        if (viewServices.getSelectionModel().getSelectedItem() != null)
            Database.getInstance().getListOfServices().remove(viewServices.getSelectionModel().getSelectedItem());
        viewServices.getItems().setAll(Database.getInstance().getListOfServices());
    }
}
