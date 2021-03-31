package zadanie3.model;

public class Customer {
    private String name = null;
    private String phoneNumber = null;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("Meno: %-40s, Telefónne číslo: %s",name, phoneNumber);
    }
}
