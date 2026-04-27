package com.example.additional.models;

import com.example.additional.enums.TicketStatus;

public class Ticket {
    public int id;
    public double cost;
    public int number;
    public String eventName;
    public TicketStatus status = TicketStatus.Free;
    public Integer customerId = null;

    public Ticket(int id, double cost, int number, String eventName) {
        this.id = id;
        this.cost = cost;
        this.number = number;
        this.eventName = eventName;
    }
}
