package brainacad_org.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Driver {
    private String name;
    private int experience;
    private double earnings;
    private boolean isFree;
}
