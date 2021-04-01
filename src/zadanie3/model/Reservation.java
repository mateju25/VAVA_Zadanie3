package zadanie3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation implements Serializable {
    private Customer responsibleCust = null;
    private Room wantedRoom = null;
    private Date accomodatedFrom = null;
    private Date accomodatedTo = null;

    public Reservation(Customer responsibleCust, Room wantedRoom, Date accomodatedFrom, Date accomodatedTo) {
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

    public Date getAccomodatedFrom() {
        return accomodatedFrom;
    }

    public Date getAccomodatedTo() {
        return accomodatedTo;
    }

    public double getTotalValue() {
        long days = Math.abs(accomodatedFrom.getTime() - accomodatedTo.getTime());
        double free = 1;
        if (TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS) >= 10)
            free = 0.9;
        return TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS) * wantedRoom.getCategory().getCost() * free;
    }

    public void setResponsibleCust(Customer responsibleCust) {
        this.responsibleCust = responsibleCust;
    }

    public void setWantedRoom(Room wantedRoom) {
        this.wantedRoom = wantedRoom;
    }

    @Override
    public String toString() {
        return "Rezervácia na meno " + responsibleCust.getName() + " Izba: " + wantedRoom.getIdentifier();
    }
}
