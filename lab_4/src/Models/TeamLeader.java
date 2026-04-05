package Models;

import Interfaces.IPart;
import Interfaces.IWorker;

public class TeamLeader implements IWorker {
    private String name;

    public TeamLeader(String name) {
        this.name = name;
    }

    @Override
    public void work(House house) {
        System.out.println("\n--- TEAM LEADER " + name + " REPORT ---");
        int total = house.getTotalPartsNeeded();
        int built = house.getBuiltParts().size();
        System.out.println("Built parts: " + built + " / " + total);
        double percentage = (double) built / total * 100;
        System.out.printf("Progress: %.2f%%\n", percentage);
        System.out.println("Completed components:");
        if (built == 0) {
            System.out.println("- None");
        } else {
            for (IPart part : house.getBuiltParts()) {
                System.out.println("- " + part.getName());
            }
        }
        System.out.println("--------------------------------\n");
    }
}