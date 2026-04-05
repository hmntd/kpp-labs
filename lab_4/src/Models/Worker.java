package Models;

import Interfaces.IPart;
import Interfaces.IWorker;
import java.util.List;

public class Worker implements IWorker {
    private String name;

    public Worker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void work(House house) {
        if (house.isFinished()) {
            return;
        }

        List<IPart> parts = house.getBuiltParts();
        int basements = 0;
        int walls = 0;
        int doors = 0;
        int windows = 0;
        int roofs = 0;

        for (IPart part : parts) {
            if (part instanceof Basement) basements++;
            else if (part instanceof Wall) walls++;
            else if (part instanceof Door) doors++;
            else if (part instanceof Window) windows++;
            else if (part instanceof Roof) roofs++;
        }

        if (basements == 0) {
            house.addPart(new Basement());
            System.out.println("Worker " + name + " built a Basement.");
        } else if (walls < 4) {
            house.addPart(new Wall());
            System.out.println("Worker " + name + " built a Wall.");
        } else if (doors < 1) {
            house.addPart(new Door());
            System.out.println("Worker " + name + " installed a Door.");
        } else if (windows < 4) {
            house.addPart(new Window());
            System.out.println("Worker " + name + " installed a Window.");
        } else if (roofs == 0) {
            house.addPart(new Roof());
            System.out.println("Worker " + name + " built a Roof.");
        }
    }
}