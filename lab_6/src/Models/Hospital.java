package Models;

public class Hospital extends Building {
    private int bedsCount;

    public Hospital(int addressNumber, int bedsCount) {
        super(addressNumber);
        this.bedsCount = bedsCount;
    }

    @Override
    public void parseData(String data) throws Exception {
        this.bedsCount = Integer.parseInt(data.trim());
    }

    @Override
    public String toCsvRow() {
        return "HOSPITAL;" + addressNumber + ";" + bedsCount;
    }

    @Override
    public String toString() {
        return "[Hospital] " + super.toString() + " | Beds: " + bedsCount;
    }
}
