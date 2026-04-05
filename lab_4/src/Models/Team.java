package Models;

import Interfaces.IWorker;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<IWorker> teamMembers;

    public Team() {
        this.teamMembers = new ArrayList<>();
    }

    public void addMember(IWorker member) {
        teamMembers.add(member);
    }

    public void startBuilding(House house) {
        System.out.println("--- TEAM STARTS BUILDING ---");
        int index = 0;
        int membersCount = teamMembers.size();

        if (membersCount == 0) {
            System.out.println("No workers in the team!");
            return;
        }

        while (!house.isFinished()) {
            IWorker member = teamMembers.get(index % membersCount);
            member.work(house);
            index++;
        }

        System.out.println("\n--- FINAL CONSTRUCTION PHASE ---");
        System.out.println("House construction completed!");
    }
}