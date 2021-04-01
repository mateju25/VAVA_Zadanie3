package zadanie3.model;


import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Accomodation implements Serializable {
    private Payment paid = null;
    private Date accomodatedFrom = null;
    private Date accomodatedTo = null;
    private List<Service> usedServices = new ArrayList<>();
    private Room usedRoom = null;
    private Customer responsibleCust = null;

    public Accomodation(Customer responsibleCust, Room usedRoom, Date accomodatedFrom, Date accomodatedTo) {
        this.accomodatedFrom = accomodatedFrom;
        this.accomodatedTo = accomodatedTo;
        this.usedRoom = usedRoom;
        this.responsibleCust = responsibleCust;
    }

    public Date getAccomodatedFrom() {
        return accomodatedFrom;
    }

    public Date getAccomodatedTo() {
        return accomodatedTo;
    }

    public List<Service> getUsedServices() {
        return usedServices;
    }

    public Room getUsedRooms() {
        return usedRoom;
    }

    public Customer getResponsibleCust() {
        return responsibleCust;
    }

    public Payment getPaid() {
        return paid;
    }

    public void setPaid(Payment paid) {
        this.paid = paid;
    }

    public void setUsedServices(List<Service> usedServices) {
        this.usedServices = usedServices;
    }

    public void setUsedRoom(Room usedRoom) {
        this.usedRoom = usedRoom;
    }

    public void setResponsibleCust(Customer responsibleCust) {
        this.responsibleCust = responsibleCust;
    }

    public double getTotalValue() {
        long days = Math.abs(accomodatedFrom.getTime() - accomodatedTo.getTime());
        int services = 0;
        for (Service serv :
                usedServices) {
            services += serv.getCost();
        }
        double free = 1;
        if (TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS) >= 10)
            free = 0.9;
        return TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS) * usedRoom.getCategory().getCost() * free + services;
    }

    @Override
    public String toString() {
        return "Ubytovanie na meno " + responsibleCust.getName() + " Izba: " + usedRoom.getIdentifier();
    }
}
