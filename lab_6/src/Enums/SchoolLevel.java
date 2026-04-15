package Enums;

import java.util.Random;

public enum SchoolLevel {
    GENERAL(200, 500),
    GYMNASIUM(400, 800),
    LYCEUM(700, 1200);

    private final int minStudents;
    private final int maxStudents;

    SchoolLevel(int min, int max) {
        this.minStudents = min;
        this.maxStudents = max;
    }

    public int generateRandomStudents() {
        return new Random().nextInt((maxStudents - minStudents) + 1) + minStudents;
    }
}
