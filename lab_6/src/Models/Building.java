package Models;

abstract public class Building {
    protected int addressNumber;

    public Building() {
    }

    public Building(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public abstract void parseData(String data) throws Exception;

    public abstract String toCsvRow();

    @Override
    public String toString() {
        return "Address: " + addressNumber;
    }
}
