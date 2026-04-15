package Models;

import java.util.UUID;

public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String position;

    public Employee(String firstName, String lastName, int age, String position) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
    }

    public Employee(String id, String firstName, String lastName, int age, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Converts object to a CSV string for saving to file
    public String toCsvRow() {
        return String.join(",", id, firstName, lastName, String.valueOf(age), position);
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Ім'я: %s | Прізвище:%s | Вік: %d | Посада: %s",
                id.substring(0, 8), firstName, lastName, age, position);
    }
}
