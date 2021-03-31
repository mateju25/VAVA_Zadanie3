package zadanie3.model;

public class Service {
    private String description = null;
    private double cost = 0;

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

    @Override
    public String toString() {
        return String.format("Služba: %-40s, Cena: %s",description, String.valueOf(cost));
    }
}
