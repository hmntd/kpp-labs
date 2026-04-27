package com.example.additional;

import com.example.additional.enums.TicketStatus;
import com.example.additional.generators.DataGenerator;
import com.example.additional.models.Customer;
import com.example.additional.models.Event;
import com.example.additional.models.Place;
import com.example.additional.models.Ticket;
import com.example.additional.services.BookingService;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookingSystemTest {
    private BookingService service;

    @Before
    public void setUp() {
        service = new BookingService();
        DataGenerator.seedData(service);
    }

    @Test
    public void testUpcomingEventsSearch() {
        List<Event> upcoming = service.findUpcomingEvents();
        assertFalse("Список майбутніх подій не повинен бути порожнім", upcoming.isEmpty());
        assertTrue(upcoming.get(0).eventDate.after(new Date()));
    }

    @Test
    public void testTicketPurchase() throws Exception {
        Event event = service.findUpcomingEvents().get(0);
        Ticket ticket = service.findFreeTickets(event.name).get(0);
        Customer customer = new Customer(99, "Test User", "test@mail.com", "123");

        service.buyTicket(customer, ticket);

        assertEquals(TicketStatus.Sold, ticket.status);
        assertEquals(1, customer.tickets.size());
        assertEquals(customer.id, (int)ticket.customerId);
    }

    @Test(expected = Exception.class)
    public void testDuplicateEventConflict() throws Exception {
        Place place = new Place(10, "Cinema", "Street 1");
        Date date = new Date();

        service.addEvent(new Event(1, "Movie 1", date, place));
        service.addEvent(new Event(2, "Movie 2", date, place));
    }
}
