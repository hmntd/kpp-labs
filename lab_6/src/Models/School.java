package Models;

import Enums.SchoolLevel;

public class School extends Building {
    private SchoolLevel level;
    private int studentCount;

    public School(int addressNumber, SchoolLevel level) {
        super(addressNumber);
        this.level = level;
        this.studentCount = level.generateRandomStudents();
    }

    @Override
    public void parseData(String data) throws Exception {
        this.level = SchoolLevel.valueOf(data.trim().toUpperCase());
        this.studentCount = this.level.generateRandomStudents();
    }

    @Override
    public String toCsvRow() {
        return "SCHOOL;" + addressNumber + ";" + level;
    }

    @Override
    public String toString() {
        return "[School] " + super.toString() + " | Level: " + level + " | Students: " + studentCount;
    }
}
