package zadanie3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Database single_instance = null;
    private List<Customer> listOfCustomers = new ArrayList<>();
    private List<Reservation> listOfReservations = new ArrayList<>();
    private List<Accomodation> listOfAccomodations = new ArrayList<>();
    private Map<String, Room> listOfRooms = new HashMap<>();
    private List<RoomCategory> listOfRoomCategories = new ArrayList<>();
    private List<Service> listOfServices = new ArrayList<>();
    private List<Payment> listOfPayments = new ArrayList<>();

    private Database() {
        listOfCustomers.add(new Customer("Matej Delincak", "+421999535656"));
        listOfRoomCategories.add(new RoomCategory("2-lozkova izba", 35));
        listOfRoomCategories.add(new RoomCategory("1-lozkova izba", 20));
        listOfRooms.put("P1I1", new Room(listOfRoomCategories.get(0), "P1I1"));
        listOfRooms.put("P1I2", new Room(listOfRoomCategories.get(1), "P1I2"));
        listOfRooms.put("P2I1", new Room(listOfRoomCategories.get(1), "P2I1"));
        listOfServices.add(new Service("Masaz", 10));
        listOfServices.add(new Service("All Inclusive", 20));
        listOfServices.add(new Service("Pool", 5));
    }

    public static Database getInstance()
    {
        if (single_instance == null) {
            single_instance = new Database();
        }

        return single_instance;
    }


    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public List<Reservation> getListOfReservations() {
        return listOfReservations;
    }

    public List<Accomodation> getListOfAccomodations() {
        return listOfAccomodations;
    }

    public Map<String, Room> getListOfRooms() {
        return listOfRooms;
    }

    public List<RoomCategory> getListOfRoomCategories() {
        return listOfRoomCategories;
    }

    public List<Service> getListOfServices() {
        return listOfServices;
    }

    public List<Payment> getListOfPayments() {
        return listOfPayments;
    }
}
