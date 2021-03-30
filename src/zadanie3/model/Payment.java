package zadanie3.model;

import java.time.LocalDateTime;

public class Payment {
    private LocalDateTime paidDate = LocalDateTime.now();
    private Accomodation infoAboutAccom = null;
    private boolean isPaidByCard;

    public Payment(Accomodation infoAboutAccom, boolean isPaidByCard) {
        this.infoAboutAccom = infoAboutAccom;
        this.isPaidByCard = isPaidByCard;
    }

    public LocalDateTime getPaidDate() {
        return paidDate;
    }

    public Accomodation getInfoAboutAccom() {
        return infoAboutAccom;
    }

    public boolean isPaidByCard() {
        return isPaidByCard;
    }
}
