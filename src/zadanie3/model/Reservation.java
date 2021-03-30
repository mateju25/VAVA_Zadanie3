package zadanie3.model;

import java.time.LocalDate;

public class Reservation {
    private Customer responsibleCust = null;
    private Room wantedRoom = null;
    private LocalDate accomodatedFrom = null;
    private LocalDate accomodatedTo = null;

    public Reservation(Customer responsibleCust, Room wantedRoom, LocalDate accomodatedFrom, LocalDate accomodatedTo) {
        this.responsibleCust = responsibleCust;
        this.wantedRoom = wantedRoom;
        this.accomodatedFrom = accomodatedFrom;
        this.accomodatedTo = accomodatedTo;
    }

    public Customer getResponsibleCust() {
        return responsibleCust;
    }

    public Room getWantedRoom() {
        return wantedRoom;
    }

    public LocalDate getAccomodatedFrom() {
        return accomodatedFrom;
    }

    public LocalDate getAccomodatedTo() {
        return accomodatedTo;
    }
}
