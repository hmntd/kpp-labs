package Models;

public class Customer {
    private String name;
    private boolean hasReservation;

    public Customer(String name, boolean hasReservation) {
        this.name = name;
        this.hasReservation = hasReservation;
    }

    public String getName() {
        return name;
    }

    public boolean hasReservation() {
        return hasReservation;
    }

    public String toCsvRow(String status) {
        return status + "," + name + "," + hasReservation;
    }

    @Override
    public String toString() {
        return name + (hasReservation ? " (Reserved)" : "");
    }
}
