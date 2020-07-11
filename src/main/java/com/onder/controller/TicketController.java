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

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok().body(ticketService.getAllTicket());
    }

    @GetMapping("/tickets/{ticketNumber}")
    public ResponseEntity<Ticket> getByTicketNumber(@PathVariable long ticketNumber) {
        return ResponseEntity.ok().body(ticketService.getByTicketNumber(ticketNumber));
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable long id) {
        return ResponseEntity.ok().body(ticketService.getTicketById(id));
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok().body(this.ticketService.createTicket(ticket));
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable long id, Ticket ticket) {
        return ResponseEntity.ok().body(this.ticketService.updateTicket(ticket));
    }

    @DeleteMapping("/tickets/{id}")
    public HttpStatus deleteTicket(@PathVariable long id) {
        this.ticketService.deleteTicket(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/tickets/{ticketNumber}")
    public HttpStatus deleteByTicketNumber(@PathVariable long ticketNumber) {
        this.ticketService.deleteTicket(ticketNumber);
        return HttpStatus.OK;
    }
}
