package zadanie3.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Payment implements Serializable {
    private Date paidDate = null;
    private boolean isPaidByCard;
    private String description = "";

    public Payment(Date paidDate, boolean isPaidByCard) {
        this.paidDate = paidDate;
        this.isPaidByCard = isPaidByCard;
    }

    public void refreshDescription() {
        if (Database.getInstance().getListOfAccomodations().stream().filter(accomodation -> accomodation.getPaid() == this).collect(Collectors.toList()).size() != 0) {
            Accomodation infoAboutAccom = Database.getInstance().getListOfAccomodations().stream().filter(accomodation -> accomodation.getPaid() == this).collect(Collectors.toList()).get(0);
            if (isPaidByCard)
                description = "Zákazník " + infoAboutAccom.getResponsibleCust().getName() + " v dni " + new SimpleDateFormat("dd.MM.yyyy").format(paidDate) + " zaplatil za izbu " + infoAboutAccom.getUsedRooms().getIdentifier() + " kartou";
            else
                description = "Zákazník " + infoAboutAccom.getResponsibleCust().getName() + " v dni " + new SimpleDateFormat("dd.MM.yyyy").format(paidDate) + " zaplatil za izbu " + infoAboutAccom.getUsedRooms().getIdentifier() + " v hotovosti";
        }
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public boolean isPaidByCard() {
        return isPaidByCard;
    }

    @Override
    public String toString() {
        return description;
    }
}
