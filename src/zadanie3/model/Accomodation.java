package zadanie3.model;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Accomodation {
    private Reservation reservation = null;
    private LocalDate accomodatedFrom = null;
    private LocalDate accomodatedTo = null;
    private Map<String, Service> usedServices = new HashMap<>();
    private Room usedRoom = null;
    private Customer responsibleCust = null;

    public Accomodation(Reservation reservation, LocalDate accomodatedFrom, LocalDate accomodatedTo, Map<String, Service> usedServices, Room usedRoom, Customer responsibleCust) {
        this.reservation = reservation;
        this.accomodatedFrom = accomodatedFrom;
        this.accomodatedTo = accomodatedTo;
        this.usedServices = usedServices;
        this.usedRoom = usedRoom;
        this.responsibleCust = responsibleCust;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public LocalDate getAccomodatedFrom() {
        return accomodatedFrom;
    }

    public LocalDate getAccomodatedTo() {
        return accomodatedTo;
    }

    public Map<String, Service> getUsedServices() {
        return usedServices;
    }

    public Room getUsedRooms() {
        return usedRoom;
    }

    public Customer getResponsibleCust() {
        return responsibleCust;
    }

    public void setUsedServices(Map<String, Service> usedServices) {
        this.usedServices = usedServices;
    }
}
