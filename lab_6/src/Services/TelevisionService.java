package Services;

import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Models.Television;

public class TelevisionService {
    private final List<Television> tvList;

    public TelevisionService(List<Television> initialData) {
        this.tvList = initialData;
    }

    public void addTelevision(Television tv) {
        tvList.add(tv);
    }

    public List<Television> getAllTvs() {
        return tvList;
    }

    public void printAll() {
        tvList.forEach(System.out::println);
    }

    public void printByDiagonal(double diagonal) {
        tvList.stream()
                .filter(tv -> tv.getDiagonal() == diagonal)
                .forEach(System.out::println);
    }

    public void printByManufacturer(String manufacturer) {
        tvList.stream()
                .filter(tv -> tv.getManufacturer().equalsIgnoreCase(manufacturer))
                .forEach(System.out::println);
    }

    public void printCurrentYearSmallScreenMinPrice(double minPrice) {
        int currentYear = Year.now().getValue();
        tvList.stream()
                .filter(tv -> tv.getYear() == currentYear)
                .filter(tv -> tv.getDiagonal() <= 30)
                .filter(tv -> tv.getPrice() >= minPrice)
                .forEach(System.out::println);
    }

    public void printMoreExpensiveThan(double price) {
        tvList.stream()
                .filter(tv -> tv.getPrice() > price)
                .forEach(System.out::println);
    }

    public void printSortedByPriceAsc() {
        tvList.stream()
                .sorted(Comparator.comparingDouble(Television::getPrice))
                .forEach(System.out::println);
    }

    public void printSortedByDiagonalDesc() {
        tvList.stream()
                .sorted(Comparator.comparingDouble(Television::getDiagonal).reversed())
                .forEach(System.out::println);
    }

    public void printGroupedByManufacturer() {
        Map<String, List<Television>> grouped = tvList.stream()
                .collect(Collectors.groupingBy(Television::getManufacturer));

        grouped.forEach((mfg, list) -> {
            System.out.println("\n--- Manufacturer: " + mfg.toUpperCase() + " ---");
            list.forEach(tv -> System.out.println("  " + tv));
        });
    }

    public void printTop5Expensive() {
        tvList.stream()
                .sorted(Comparator.comparingDouble(Television::getPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    public void printTop3SmallestDiagonal() {
        tvList.stream()
                .sorted(Comparator.comparingDouble(Television::getDiagonal))
                .limit(3)
                .forEach(System.out::println);
    }

    public void printLastMostExpensive40Inch() {
        double maxPrice = tvList.stream()
                .filter(tv -> tv.getDiagonal() == 40.0)
                .mapToDouble(Television::getPrice)
                .max()
                .orElse(-1);

        if (maxPrice == -1) {
            System.out.println("No 40-inch TVs found.");
            return;
        }

        tvList.stream()
                .filter(tv -> tv.getDiagonal() == 40.0 && tv.getPrice() == maxPrice)
                .reduce((first, second) -> second)
                .ifPresent(tv -> {
                    System.out.println("Last Most Expensive 40\" TV found:");
                    System.out.println(tv);
                });
    }
}
