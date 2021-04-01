package zadanie3.model;

import java.io.*;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Database {
    private  final static Logger LOGGER = Logger.getLogger(Database.class.getName());

    private static Database single_instance = null;
    private Date now = new Date();
    private List<Customer> listOfCustomers = new ArrayList<>();
    private List<Reservation> listOfReservations = new ArrayList<>();
    private List<Accomodation> listOfAccomodations = new ArrayList<>();
    private Map<String, Room> listOfRooms = new HashMap<>();
    private List<RoomCategory> listOfRoomCategories = new ArrayList<>();
    private List<Service> listOfServices = new ArrayList<>();
    private List<Payment> listOfPayments = new ArrayList<>();

    private Database() {}

    public static Database getInstance()
    {
        if (single_instance == null) {
            single_instance = new Database();
            single_instance.deserialize();
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

    public List<Room> getListOfRooms(Date from, Date to) {
        List<Room> result = new ArrayList<>(listOfRooms.values());
        for (Reservation reserv: listOfReservations) {
            if (from.before(reserv.getAccomodatedTo()) && (reserv.getAccomodatedFrom().before(to))) {
                result.remove(reserv.getWantedRoom());
            }
        }
        for (Accomodation accomo: listOfAccomodations) {
            if (from.before(accomo.getAccomodatedTo()) && (accomo.getAccomodatedFrom().before(to))) {
                result.remove(accomo.getUsedRooms());
            }
        }
        return result;
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

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    private void serializeObject(Object obj, String file) throws IOException {
        ObjectOutputStream outB = new ObjectOutputStream(new FileOutputStream(file));
        outB.writeObject(obj);
        outB.close();
    }

    public void serialize() {
        String file = "Data/customer.out";
        try {
            serializeObject(listOfCustomers, file);
            file = "Data/roomcategories.out";
            serializeObject(listOfRoomCategories, file);
            file = "Data/rooms.out";
            serializeObject(listOfRooms, file);
            file = "Data/reservations.out";
            serializeObject(listOfReservations, file);
            file = "Data/accomodations.out";
            serializeObject(listOfAccomodations, file);
            file = "Data/services.out";
            serializeObject(listOfServices, file);
            file = "Data/payments.out";
            serializeObject(listOfPayments, file);
            LOGGER.log(Level.FINEST, "Všetky dáta uložené.");
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Dátový súbor " + file + " nenájdený.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Do dátového súboru " + file + " sa nedá zapísať.");
        }
    }

    public void deserialize() {
        String file = "Data/customer.out";
        try {
            ObjectInputStream inB = new ObjectInputStream(new FileInputStream(file));
            listOfCustomers = (List<Customer>) inB.readObject();
            inB.close();

            file = "Data/services.out";
            inB = new ObjectInputStream(new FileInputStream(file));
            listOfServices = (List<Service>) inB.readObject();
            inB.close();

            file = "Data/payments.out";
            inB = new ObjectInputStream(new FileInputStream(file));
            listOfPayments = (List<Payment>) inB.readObject();
            inB.close();

            file = "Data/roomcategories.out";
            inB = new ObjectInputStream(new FileInputStream(file));
            listOfRoomCategories = (List<RoomCategory>) inB.readObject();
            inB.close();

            file = "Data/rooms.out";
            inB = new ObjectInputStream(new FileInputStream(file));
            Map<String, Room> temp = (Map<String, Room>) inB.readObject();
            for (Room item :
                    temp.values()) {
                item.setCategory(listOfRoomCategories.stream().filter(roomCategory -> roomCategory.getDescription().equals(item.getCategory().getDescription())).collect(Collectors.toList()).get(0));
            }
            listOfRooms = temp;
            inB.close();

            file = "Data/reservations.out";
            inB = new ObjectInputStream(new FileInputStream(file));
            List<Reservation> temp2 = (List<Reservation>) inB.readObject();
            for (Reservation item :
                    temp2) {
                item.setWantedRoom(listOfRooms.values().stream().filter(room -> room.getIdentifier().equals(item.getWantedRoom().getIdentifier())).collect(Collectors.toList()).get(0));
                item.setResponsibleCust(listOfCustomers.stream().filter(cust -> cust.getName().equals(item.getResponsibleCust().getName())).collect(Collectors.toList()).get(0));
            }
            inB.close();

            file = "Data/accomodations.out";
            inB = new ObjectInputStream(new FileInputStream(file));
            List<Accomodation> temp3 = (List<Accomodation>) inB.readObject();
            for (Accomodation item :
                    temp3) {
                item.setUsedRoom(listOfRooms.values().stream().filter(room -> room.getIdentifier().equals(item.getUsedRooms().getIdentifier())).collect(Collectors.toList()).get(0));
                item.setResponsibleCust(listOfCustomers.stream().filter(cust -> cust.getName().equals(item.getResponsibleCust().getName())).collect(Collectors.toList()).get(0));
            }
            listOfAccomodations = temp3;
//            listOfPayments.addAll(listOfAccomodations.stream().map(Accomodation::getPaid).collect(Collectors.toList()));
            inB.close();
//            LOGGER.setLevel(Level.FINEST);
//            ConsoleHandler handler = new ConsoleHandler();
//            handler.setLevel(Level.FINEST);
//            LOGGER.addHandler(handler);
            LOGGER.log(Level.FINEST, "Všetky dáta načítané správne.");
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Dátový súbor " + file + " nenájdený.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Dátový súbor " + file + " sa nedá prečítať.");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Dáta v súbore " + file + " sa nezhodujú s aktuálnym modelom aplikácie.");
        }
    }
}

