package Services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Models.Employee;

public class CorporationService {
    private List<Employee> employees;
    private boolean isModified = false;

    public CorporationService(List<Employee> initialData) {
        this.employees = initialData;
    }

    public boolean isModified() {
        return isModified;
    }

    public void resetModifiedFlag() {
        this.isModified = false;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
        isModified = true;
    }

    public boolean deleteEmployee(String partialId) {
        boolean removed = employees.removeIf(emp -> emp.getId().startsWith(partialId));
        if (removed)
            isModified = true;
        return removed;
    }

    public Employee findById(String partialId) {
        return employees.stream()
                .filter(emp -> emp.getId().startsWith(partialId))
                .findFirst()
                .orElse(null);
    }

    public void markAsModified() {
        this.isModified = true;
    }

    public List<Employee> searchByLastName(String lastName) {
        return employees.stream()
                .filter(emp -> emp.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public List<Employee> filterByLastNameLetter(String letter) {
        return employees.stream()
                .filter(emp -> emp.getLastName().toLowerCase().startsWith(letter.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Employee> getSortedByAge() {
        List<Employee> sorted = new ArrayList<>(employees);
        sorted.sort(Comparator.comparingInt(Employee::getAge));
        return sorted;
    }
}
