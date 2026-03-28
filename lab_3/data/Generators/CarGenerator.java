package data.Generators;

import java.time.LocalDateTime;
import java.util.Random;

import Models.Car;

public class CarGenerator {
    private static final String[] BRANDS = {"Mercedes", "Honda", "BMW", "Ford", "Audi", "Aston Martin", "McLaren"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Trump", "Brown", "Black", "Pink"};
    private static final String[] ADDRESSES = {"123 Main St", "322 Main St", "100 Main St", "321 Main St"};
    
    private static final Random RND = new Random();

    public static Car generateRandomCar(int spotNumber) {
        String brand = BRANDS[RND.nextInt(BRANDS.length)];
        String lastName = LAST_NAMES[RND.nextInt(LAST_NAMES.length)];
        String address = ADDRESSES[RND.nextInt(ADDRESSES.length)];
        
        String plate = String.format("%c%c%04d%c%c", 
            (char)(RND.nextInt(26) + 'A'), (char)(RND.nextInt(26) + 'A'),
            RND.nextInt(10000), 
            (char)(RND.nextInt(26) + 'A'), (char)(RND.nextInt(26) + 'A'));

        boolean isPresent = RND.nextBoolean();
        
        LocalDateTime time = LocalDateTime.now().minusHours(RND.nextInt(24)).minusMinutes(RND.nextInt(60));

        return new Car(brand, lastName, address, plate, spotNumber, isPresent, time);
    }

    public static Car[] generateCarArray(int size) {
        Car[] cars = new Car[size];
        for (int i = 0; i < size; i++) {
            cars[i] = generateRandomCar(i + 1); 
        }
        return cars;
    }
}
