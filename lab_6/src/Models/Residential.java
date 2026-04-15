package Models;

public class Residential extends Building {
    private int residentsCount;

    public Residential(int addressNumber, int residentsCount) {
        super(addressNumber);
        this.residentsCount = residentsCount;
    }

    @Override
    public void parseData(String data) throws Exception {
        this.residentsCount = Integer.parseInt(data.trim());
    }

    @Override
    public String toCsvRow() {
        return "RESIDENTIAL;" + addressNumber + ";" + residentsCount;
    }

    public int getResidentsCount() {
        return residentsCount;
    }

    @Override
    public String toString() {
        return "[Residential] " + super.toString() + " | Residents: " + residentsCount;
    }
}
