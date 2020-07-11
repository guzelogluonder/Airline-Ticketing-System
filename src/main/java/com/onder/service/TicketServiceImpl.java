package com.onder.service;

import com.onder.exception.ResourceNotFoundException;
import com.onder.model.Flight;
import com.onder.model.Route;
import com.onder.model.Ticket;
import com.onder.repository.FlightRepository;
import com.onder.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        if (StringUtils.isEmpty(ticket.getOriginIataCode())) {
            throw new ResourceNotFoundException("Route not found with origin: " + ticket.getOriginIataCode());
        }
        if (StringUtils.isEmpty(ticket.getDestinationIataCode())) {
            throw new ResourceNotFoundException("Route not found with origin: " + ticket.getDestinationIataCode());
        }
        Flight flight = flightRepository.getByOriginIataCode(ticket.getOriginIataCode());
        Flight flight1 = flightRepository.getByDestinationIataCode(ticket.getDestinationIataCode());
        if (flight == null) {
            throw new IllegalArgumentException("Invalid airline code!");
        }
        if (flight1 == null) {
            throw new IllegalArgumentException("Invalid airline code!");
        }
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Optional<Ticket> ticketDb = this.ticketRepository.findById(ticket.getId());
        if (ticketDb.isPresent()) {
            Ticket ticketUpdate = ticketDb.get();
            ticketUpdate.setId(ticket.getId());
            ticketUpdate.setOriginIataCode(ticket.getOriginIataCode());
            ticketUpdate.setDestinationIataCode(ticket.getDestinationIataCode());
            ticketRepository.save(ticketUpdate);
            return ticketUpdate;
        } else {
            throw new ResourceNotFoundException("Route not found with id: " + ticket.getId());
        }
    }

    @Override
    public List<Ticket> getAllTicket() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        Optional<Ticket> ticketDb = this.ticketRepository.findById(ticketId);
        if (ticketDb.isPresent()) {
            return ticketDb.get();
        } else {
            throw new ResourceNotFoundException("Route not found with id: " + ticketDb);
        }
    }

    @Override
    public void deleteTicket(Long id) {
        Optional<Ticket> ticketDb = this.ticketRepository.findById(id);
        if (ticketDb.isPresent()) {
            this.ticketRepository.delete(ticketDb.get());
        } else {
            throw new ResourceNotFoundException("Route not found with id: " + id);

        }
    }
}
