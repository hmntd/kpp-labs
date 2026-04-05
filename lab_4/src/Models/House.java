package Models;

import Interfaces.IPart;
import java.util.ArrayList;
import java.util.List;

public class House {
    private List<IPart> builtParts;
    private final int totalPartsNeeded = 11; // 1 Basement, 4 Walls, 1 Door, 4 Windows, 1 Roof

    public House() {
        this.builtParts = new ArrayList<>();
    }

    public List<IPart> getBuiltParts() {
        return builtParts;
    }

    public void addPart(IPart part) {
        builtParts.add(part);
    }

    public boolean isFinished() {
        return builtParts.size() == totalPartsNeeded;
    }

    public int getTotalPartsNeeded() {
        return totalPartsNeeded;
    }
}