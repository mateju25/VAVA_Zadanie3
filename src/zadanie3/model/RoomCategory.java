package zadanie3.model;

public class RoomCategory {
    private String description = null;
    private double cost = 0;

    public RoomCategory(String description, double cost) {
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
        return description;
    }
}
