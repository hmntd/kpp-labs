package Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import Models.Customer;

public class CafeService {
    private final Queue<Customer> regularQueue = new LinkedList<>();
    private final Queue<Customer> reservedQueue = new LinkedList<>();
    private final List<Customer> seatedCustomers = new ArrayList<>();

    private final int TOTAL_TABLES = 5;

    public CafeService(CafeFileService fileService, String filePath) {
        fileService.loadState(filePath, seatedCustomers, reservedQueue, regularQueue);
        trySeatCustomers();
    }

    public void addCustomer(Customer customer) {
        if (customer.hasReservation()) {
            reservedQueue.offer(customer);
            System.out.println(customer.getName() + " joined the PRIORITY queue.");
        } else {
            regularQueue.offer(customer);
            System.out.println(customer.getName() + " joined the standard queue.");
        }
        trySeatCustomers();
    }

    public void freeTable(String customerName) throws Exception {
        Customer leavingCustomer = seatedCustomers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(customerName))
                .findFirst()
                .orElseThrow(() -> new Exception(
                        "Error: Customer '" + customerName + "' is not currently seated at a table."));

        seatedCustomers.remove(leavingCustomer);
        System.out.println(leavingCustomer.getName() + " has left. A table is now free!");

        trySeatCustomers();
    }

    private void trySeatCustomers() {
        while (seatedCustomers.size() < TOTAL_TABLES) {
            if (!reservedQueue.isEmpty()) {
                Customer c = reservedQueue.poll();
                seatedCustomers.add(c);
                System.out.println(">>> SEATED: " + c.getName() + " (from Priority Reservation)");
            } else if (!regularQueue.isEmpty()) {
                Customer c = regularQueue.poll();
                seatedCustomers.add(c);
                System.out.println(">>> SEATED: " + c.getName() + " (from Standard Queue)");
            } else {
                break;
            }
        }
    }

    public void displayStatus() {
        System.out.println("\n--- CAFE STATUS ---");
        System.out.println("Tables Occupied: " + seatedCustomers.size() + "/" + TOTAL_TABLES);

        String seatedStr = seatedCustomers.stream()
                .map(Customer::toString)
                .collect(Collectors.joining(" | "));
        System.out.println("Seated Customers: [" + (seatedStr.isEmpty() ? "None" : seatedStr) + "]");

        String resQStr = reservedQueue.stream()
                .map(Customer::getName)
                .collect(Collectors.joining(" -> "));
        System.out.println("Priority Queue (Reservations): [" + (resQStr.isEmpty() ? "Empty" : resQStr) + "]");

        String regQStr = regularQueue.stream()
                .map(Customer::getName)
                .collect(Collectors.joining(" -> "));
        System.out.println("Standard Queue: [" + (regQStr.isEmpty() ? "Empty" : regQStr) + "]");
        System.out.println("-------------------");
    }

    public List<Customer> getSeatedCustomers() {
        return seatedCustomers;
    }

    public Collection<Customer> getReservedQueue() {
        return reservedQueue;
    }

    public Collection<Customer> getRegularQueue() {
        return regularQueue;
    }
}
