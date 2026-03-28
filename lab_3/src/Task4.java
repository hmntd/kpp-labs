import Factories.AnimalFactory;
import Services.ZooManager;

public class Task4 {
    public static void main(String[] args) {
        ZooManager zooManager = new ZooManager();

        // Create animals with specific names
        zooManager.addAnimal(AnimalFactory.createTiger("Polosatiy"));
        zooManager.addAnimal(AnimalFactory.createTiger("Amur"));
        zooManager.addAnimal(AnimalFactory.createCrocodile("Gena"));
        zooManager.addAnimal(AnimalFactory.createKangaroo("Poprigun"));
        zooManager.addAnimal(AnimalFactory.createKangaroo("Heisenberg"));
        zooManager.addAnimal(AnimalFactory.createKangaroo("Joey"));

        // Create random animals
        zooManager.addAnimal(AnimalFactory.createRandomTiger());
        zooManager.addAnimal(AnimalFactory.createRandomCrocodile());
        zooManager.addAnimal(AnimalFactory.createRandomKangaroo());

        zooManager.displayAllAnimalInfo();
        zooManager.countAnimalsByType();
        zooManager.calculateMonthlyFoodNeeds();
    }
}
