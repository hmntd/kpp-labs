package Models;

import java.util.ArrayList;
import java.util.List;

import Enums.ShopType;

public class Shop extends Building {
    private ShopType type;
    private List<String> departments = new ArrayList<>();

    public Shop(int addressNumber, ShopType type, List<String> departments) {
        super(addressNumber);
        this.type = type;
        this.departments = departments;
    }

    @Override
    public void parseData(String data) throws Exception {
        String[] parts = data.split(",");
        this.type = ShopType.valueOf(parts[0].trim().toUpperCase());

        this.departments.clear();
        for (int i = 1; i <= type.getDepartmentCount() && i < parts.length; i++) {
            this.departments.add(parts[i].trim());
        }
    }

    @Override
    public String toCsvRow() {
        String depts = String.join(",", departments);
        return "SHOP;" + addressNumber + ";" + type + "," + depts;
    }

    public boolean hasDepartment(String deptName) {
        return departments.stream().anyMatch(d -> d.equalsIgnoreCase(deptName));
    }

    @Override
    public String toString() {
        return "[Shop] " + super.toString() + " | Type: " + type + " | Depts: " + departments;
    }
}
