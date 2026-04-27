package com.example.additional.services;

import com.example.additional.enums.TicketStatus;
import com.example.additional.models.Customer;
import com.example.additional.models.Event;
import com.example.additional.models.Ticket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    final private List<Event> events = new ArrayList<>();
    final private List<Customer> customers = new ArrayList<>();

    public void addEvent(Event newEvent) throws Exception {
        for (Event e : events) {
            if (e.place.id == newEvent.place.id && isSameDay(e.eventDate, newEvent.eventDate)) {
                throw new Exception("Місце вже зайняте на цю дату!");
            }
        }
        events.add(newEvent);
    }

    public List<Ticket> findFreeTickets(String eventName) {
        return events.stream()
                .filter(e -> e.name.equalsIgnoreCase(eventName))
                .flatMap(e -> e.tickets.stream())
                .filter(t -> t.status == TicketStatus.Free)
                .collect(Collectors.toList());
    }

    public List<Event> findUpcomingEvents() {
        Date now = new Date();
        return events.stream()
                .filter(e -> e.eventDate.after(now))
                .sorted(Comparator.comparing(e -> e.eventDate))
                .collect(Collectors.toList());
    }

    public void buyTicket(Customer customer, Ticket ticket) throws Exception {
        if (ticket.status == TicketStatus.Sold) {
            throw new Exception("Квиток вже продано!");
        }
        ticket.status = TicketStatus.Sold;
        ticket.customerId = customer.id;
        customer.tickets.add(ticket);
    }

    private boolean isSameDay(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }
}
