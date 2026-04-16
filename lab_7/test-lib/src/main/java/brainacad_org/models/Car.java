package brainacad_org.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private String plateNumber;
    private double capacity;
    private int complexity; // Складність керування (1-10)
    private boolean isFree = true;
    private boolean isBroken = false;

    public void repair() {
        this.isBroken = false;
    }
}
