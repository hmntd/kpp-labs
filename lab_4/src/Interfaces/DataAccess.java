package Interfaces;

import Models.Car;
import java.io.IOException;

public interface DataAccess {
    void writeCarsToFile(Car[] cars, String fileName) throws IOException;
    Car[] readCarsFromFile(String fileName) throws IOException, ClassNotFoundException;
}
