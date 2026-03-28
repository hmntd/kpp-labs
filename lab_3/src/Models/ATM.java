package Models;

import Exceptions.ATMException;
import Exceptions.BillNotAvailableException;
import Exceptions.InvalidAmountException;
import Exceptions.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATM {

    private final Map<Integer, Integer> banknotes;
    private int maxWithdrawalAmount;
    private int maxNumberOfBills;

    public ATM(int maxWithdrawalAmount, int maxNumberOfBills) {
        this.maxWithdrawalAmount = maxWithdrawalAmount;
        this.maxNumberOfBills = maxNumberOfBills;
        this.banknotes = new TreeMap<>(Collections.reverseOrder());
        initializeATM();
    }

    private void initializeATM() {
        banknotes.put(1, 500);
        banknotes.put(2, 200);
        banknotes.put(5, 200);
        banknotes.put(10, 100);
        banknotes.put(20, 100);
        banknotes.put(50, 100);
        banknotes.put(100, 100);
        banknotes.put(200, 50);
        banknotes.put(500, 30);
        banknotes.put(1000, 20);
    }

    public void loadMoney(int denomination, int count) throws InvalidAmountException {
        if (!banknotes.containsKey(denomination)) {
            throw new InvalidAmountException("Invalid denomination: " + denomination);
        }
        banknotes.put(denomination, banknotes.get(denomination) + count);
    }

    public void depositMoney(int denomination, int count) throws InvalidAmountException {
        if (!banknotes.containsKey(denomination)) {
            throw new InvalidAmountException("Invalid denomination: " + denomination);
        }
        banknotes.put(denomination, banknotes.get(denomination) + count);
    }

    public Map<Integer, Integer> withdrawMoney(int amount) throws ATMException {
        if (amount > maxWithdrawalAmount) {
            throw new InvalidAmountException("Withdrawal amount exceeds the maximum limit.");
        }

        if (amount > getTotalAmount()) {
            throw new NotEnoughMoneyException("Not enough money in the ATM.");
        }

        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        int remainingAmount = amount;
        int totalBills = 0;

        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            int denomination = entry.getKey();
            int availableBills = entry.getValue();
            int billsToDispense = remainingAmount / denomination;

            if (billsToDispense > 0) {
                int actualBills = Math.min(billsToDispense, availableBills);
                result.put(denomination, actualBills);
                remainingAmount -= actualBills * denomination;
                totalBills += actualBills;
            }
        }

        if (remainingAmount > 0) {
            throw new BillNotAvailableException("Cannot dispense the requested amount with available bills.");
        }

        if (totalBills > maxNumberOfBills) {
            throw new InvalidAmountException("Number of bills to dispense exceeds the maximum limit.");
        }

        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            banknotes.put(entry.getKey(), banknotes.get(entry.getKey()) - entry.getValue());
        }

        return result;
    }

    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }
}
