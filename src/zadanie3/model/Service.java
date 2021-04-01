package zadanie3.model;

import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable {
    private String description = null;
    private double cost = 0;
    private Date used = null;

    public Service(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public Date getUsed() {
        return used;
    }

    public void setUsed(Date used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return String.format("Služba: %-40s Cena: %s",description, String.valueOf(cost));
    }
}
