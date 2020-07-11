package com.onder.service;

import com.onder.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    List<Ticket> getAllTicket();

    Ticket getTicketById(Long ticketId);

    Ticket getByTicketNumber(long ticketNumber);

    void deleteTicket(Long id);
}
