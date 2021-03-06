package zadanie3.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import zadanie3.model.Database;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController extends PrimitiveController{

    public DatePicker datePick;
    public Button btnCustomer;
    public ImageView slovakView;
    public ImageView englishView;
    public Label textDate;
    public Button btnPayment;
    public Button btnService;
    public Button btnRoom;
    public Button btnAccommodation;
    public Button btnReservation;

    private DateTimeFormatter dateTimeFormatter = null;
    private ResourceBundle bundle = null;

    public void initialize() {
        Locale.setDefault(new Locale("sk"));
        bundle = ResourceBundle.getBundle("zadanie3/gui/resources/slovak");
        datePick.setValue(Database.getInstance().getNow().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        setNewFormat();
    }

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

    public void changeDate(ActionEvent actionEvent) throws ParseException {
        Database.getInstance().setNow(Date.from(datePick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public void changeToSlovak(MouseEvent mouseEvent) {
        Locale.setDefault(new Locale("sk"));
        bundle = ResourceBundle.getBundle("zadanie3/gui/resources/slovak");
        refreshTexts();
        setNewFormat();
        datePick.setValue(Database.getInstance().getNow().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public void changeToEnglish(MouseEvent mouseEvent) {
        Locale.setDefault(new Locale("us"));
        bundle = ResourceBundle.getBundle("zadanie3/gui/resources/english");
        refreshTexts();
        setNewFormat();
        datePick.setValue(Database.getInstance().getNow().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private void refreshTexts() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(bundle.getString("DATE"));
        btnAccommodation.setText(bundle.getString("accomodation"));
        btnPayment.setText(bundle.getString("payment"));
        btnReservation.setText(bundle.getString("reservation"));
        btnRoom.setText(bundle.getString("rooms"));
        btnCustomer.setText(bundle.getString("customers"));
        btnService.setText(bundle.getString("services"));
        textDate.setText(bundle.getString("actual.date"));
    }

    private void setNewFormat() {
        datePick.setConverter(new StringConverter<LocalDate>()
        {
            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
    }
}
