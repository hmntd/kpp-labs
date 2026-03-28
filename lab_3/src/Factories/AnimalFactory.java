package Factories;

import java.util.Random;

import Models.Zoo.Crocodile;
import Models.Zoo.Kangaroo;
import Models.Zoo.Tiger;

public class AnimalFactory {
    private static final Random random = new Random();

    public static Tiger createTiger(String name) {
        return new Tiger(name);
    }

    public static Tiger createRandomTiger() {
        return new Tiger("Tiger_" + random.nextInt(1000));
    }

    public static Crocodile createCrocodile(String name) {
        return new Crocodile(name);
    }

    public static Crocodile createRandomCrocodile() {
        return new Crocodile("Crocodile_" + random.nextInt(1000));
    }

    public static Kangaroo createKangaroo(String name) {
        return new Kangaroo(name);
    }

    public static Kangaroo createRandomKangaroo() {
        return new Kangaroo("Kangaroo_" + random.nextInt(1000));
    }
}
