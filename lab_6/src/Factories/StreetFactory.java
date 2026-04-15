package Factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Enums.SchoolLevel;
import Enums.ShopType;
import Models.Building;
import Models.Buildings;

public class StreetFactory {
    public static List<Building> generateTestStreet() {
        List<Building> street = new ArrayList<>();

        street.add(new Buildings.Res(10, 150));
        street.add(new Buildings.Res(12, 300));
        street.add(new Buildings.Sch(14, SchoolLevel.LYCEUM));
        street.add(new Buildings.Hos(18, 500));

        street.add(new Buildings.Shp(20, ShopType.SMALL_STORE, Arrays.asList("Bakery")));
        street.add(new Buildings.Shp(22, ShopType.SUPERMARKET,
                Arrays.asList("Grocery", "Meat", "Electronics", "Pharmacy", "Clothing")));

        return street;
    }
}
