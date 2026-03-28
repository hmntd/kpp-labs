import Models.ATM;
import Models.Bank;
import Exceptions.ATMException;

import java.util.Map;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Bank bank = new Bank(3);
        System.out.println("Total money in the bank network: " + bank.getTotalAmountInNetwork());

        ATM atm = bank.getAtms().get(0);
        System.out.println("ATM total money: " + atm.getTotalAmount());

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter amount to withdraw: ");
            int amount = scanner.nextInt();

            Map<Integer, Integer> withdrawnMoney = atm.withdrawMoney(amount);
            System.out.println("Withdrawn money: ");
            withdrawnMoney.forEach((denomination, count) ->
                    System.out.println(denomination + " UAH: " + count + " bills"));

            System.out.println("ATM total money after withdrawal: " + atm.getTotalAmount());
        } catch (ATMException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
