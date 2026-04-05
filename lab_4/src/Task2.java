import Models.*;

public class Task2 {
    public static void main(String[] args) {
        System.out.println("--- TASK 2: HOUSE CONSTRUCTION ---");
        
        House house = new House();
        Team team = new Team();
        
        team.addMember(new Worker("John"));
        team.addMember(new Worker("Bob"));
        team.addMember(new TeamLeader("Boss"));
        
        team.startBuilding(house);
    }
}