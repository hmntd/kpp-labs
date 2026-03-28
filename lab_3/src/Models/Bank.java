package Models;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<ATM> atms;
    private final int numberOfATMs;

    public Bank(int numberOfATMs) {
        this.numberOfATMs = numberOfATMs;
        this.atms = new ArrayList<>();
        initializeATMNetwork();
    }

    private void initializeATMNetwork() {
        for (int i = 0; i < numberOfATMs; i++) {
            atms.add(new ATM(100000, 200));
        }
    }

    public int getTotalAmountInNetwork() {
        int total = 0;
        for (ATM atm : atms) {
            total += atm.getTotalAmount();
        }
        return total;
    }

    public List<ATM> getAtms() {
        return atms;
    }
}
