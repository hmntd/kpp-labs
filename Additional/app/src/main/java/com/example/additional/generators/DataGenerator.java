package com.example.additional.generators;

import com.example.additional.models.Customer;
import com.example.additional.models.Event;
import com.example.additional.models.Place;
import com.example.additional.models.Ticket;
import com.example.additional.services.BookingService;
import com.github.javafaker.Faker;

import java.util.concurrent.TimeUnit;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static void seedData(BookingService service) {
        try {
            Place palace = new Place(1, "Палац Спорту", faker.address().fullAddress());

            for (int i = 0; i < 5; i++) {
                Event event = new Event(
                        i,
                        faker.rockBand().name() + " Live",
                        faker.date().future(30, TimeUnit.DAYS),
                        palace
                );

                // Генеруємо фіксований набір квитків
                for (int j = 1; j <= 10; j++) {
                    event.tickets.add(new Ticket(
                            j,
                            500 + faker.number().randomDouble(2, 0, 1000),
                            j,
                            event.name
                    ));
                }
                service.addEvent(event);
            }

            Customer c = new Customer(1, faker.name().fullName(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone());
            service.addCustomer(c);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
