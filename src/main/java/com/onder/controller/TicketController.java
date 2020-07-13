package com.onder.controller;

import com.onder.model.Ticket;

import com.onder.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    //gets all tickets
    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok().body(ticketService.getAllTicket());
    }

    //gets ticket by ticket number
    @GetMapping("/tickets/ticketNumber/{ticketNumber}")
    public ResponseEntity<Ticket> getByTicketNumber(@PathVariable long ticketNumber) {
        return ResponseEntity.ok().body(ticketService.getByTicketNumber(ticketNumber));
    }

    //creates ticket
    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok().body(this.ticketService.createTicket(ticket));
    }

    //updates ticket by id
    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable long id, Ticket ticket) {
        return ResponseEntity.ok().body(this.ticketService.updateTicket(ticket));
    }

    //deletes ticket by ticket number
    @DeleteMapping("/tickets/ticketNumber/{ticketNumber}")
    public HttpStatus deleteByTicketNumber(@PathVariable long ticketNumber) {
        this.ticketService.deleteTicket(ticketNumber);
        return HttpStatus.OK;
    }
}
