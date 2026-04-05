package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Car implements Serializable, Comparable<Car> {
    private static final long serialVersionUID = 1L;

    private String brand;
    private String ownerLastName;
    private String ownerAddress;
    private String licensePlate;
    private int parkingSpot;
    private boolean isPresent;
    private LocalDateTime timestamp;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Car() {
        this.brand = "Unknown";
        this.ownerLastName = "Unknown";
        this.ownerAddress = "Unknown";
        this.licensePlate = "0000";
        this.parkingSpot = 0;
        this.isPresent = false;
        this.timestamp = LocalDateTime.now();
    }

    public Car(String brand, String ownerLastName, String ownerAddress,
            String licensePlate, int parkingSpot, boolean isPresent, LocalDateTime timestamp) {
        this.brand = brand;
        this.ownerLastName = ownerLastName;
        this.ownerAddress = ownerAddress;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
        this.isPresent = isPresent;
        this.timestamp = timestamp;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(int parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        String status = isPresent ? "Entry: " : "Exit:  ";
        return String.format("Spot: %02d | Plate: %-8s | Brand: %-10s | Owner: %-10s | %s %s",
                parkingSpot, licensePlate, brand, ownerLastName, status, timestamp.format(FORMATTER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Car car = (Car) o;
        return Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }

    @Override
    public int compareTo(Car other) {
        if (other == null) {
            return 1;
        }
        int timeCmp = this.timestamp.compareTo(other.timestamp);
        if (timeCmp != 0) {
            return timeCmp;
        }
        int brandCmp = this.brand.compareToIgnoreCase(other.brand);
        if (brandCmp != 0) {
            return brandCmp;
        }
        return this.licensePlate.compareToIgnoreCase(other.licensePlate);
    }
}
