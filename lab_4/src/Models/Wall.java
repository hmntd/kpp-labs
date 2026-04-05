package Models;

import Interfaces.IPart;

public class Wall implements IPart {
    @Override
    public String getName() {
        return "Wall";
    }
}