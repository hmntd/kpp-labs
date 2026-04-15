package Models;

public class Television {
    private String modelName;
    private int year;
    private double price;
    private double diagonal;
    private String manufacturer;

    public Television(String modelName, int year, double price, double diagonal, String manufacturer) {
        this.modelName = modelName;
        this.year = year;
        this.price = price;
        this.diagonal = diagonal;
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String toCsvRow() {
        return String.join(",", modelName, String.valueOf(year), String.valueOf(price), String.valueOf(diagonal),
                manufacturer);
    }

    @Override
    public String toString() {
        return String.format("TV [%-10s | %d | %.1f\" | $%.2f | Mfg: %s]",
                modelName, year, diagonal, price, manufacturer);
    }
}
