package com.example.additional.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    public int id;
    public String name;
    public Date eventDate;
    public Place place;
    public List<Ticket> tickets = new ArrayList<>();

    public Event(int id, String name, Date eventDate, Place place) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.place = place;
    }
}
