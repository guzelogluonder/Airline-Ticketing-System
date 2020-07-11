package com.onder.service;

import com.onder.exception.ResourceNotFoundException;
import com.onder.model.Flight;
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
        if (StringUtils.isEmpty(ticket.getTicketPrice())) {
            throw new ResourceNotFoundException("Route not found with origin: " + ticket.getTicketPrice());
        }

        Flight flight = flightRepository.getByOriginIataCode(ticket.getOriginIataCode());
        Flight flight1 = flightRepository.getByDestinationIataCode(ticket.getDestinationIataCode());
        Flight flight2 = flightRepository.getTicketPrice(ticket.getOriginIataCode(), ticket.getDestinationIataCode(), ticket.getAirlineTwoLetterCode());
        if (flight == null) {
            throw new IllegalArgumentException("Invalid origin Iata code!");
        }
        if (flight1 == null) {
            throw new IllegalArgumentException("Invalid Destination Iata code!");
        }
        if (flight2 == null) {
            throw new IllegalArgumentException("Invalid ticket price!");
        }
        /*
         * 1. uçağın kontenjanını al
         * 2. uçağın o anki biletli yolcu sayısını al.
         * 3. üzerine 1 ekle.
         * 4. Eğer %10 luk bir artış varsa git flight bilet fiyarını update et.
         * */
        int contingent = flight2.getContingent();
        int ticketCount = ticketRepository.getTicketsByFlight(ticket.getAirlineTwoLetterCode(), ticket.getOriginIataCode(), ticket.getDestinationIataCode()).size() + 1;
        int threshold = contingent / 10;
        if (ticketCount % threshold == 0) {
            flightRepository.updateTicketPrice(flight2.getTicketPrice() * 110 / 100, flight2.getOriginIataCode(), flight2.getDestinationIataCode(), flight2.getTwoLetterCode());
            System.out.println("TicketCount = " + ticketCount + "Threshold = " + threshold);
        }
        ticket.setTicketPrice(flight2.getTicketPrice());

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
            ticketUpdate.setAirlineTwoLetterCode(ticket.getAirlineTwoLetterCode());
            ticketUpdate.setCreditCardNumber(ticket.getCreditCardNumber());
            ticketUpdate.setTicketNumber(ticket.getTicketNumber());
            ticketUpdate.setTicketPrice(ticket.getTicketPrice());
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

    @Override
    public Ticket getByTicketNumber(long ticketNumber) {
        Optional<Ticket> ticketDb = this.ticketRepository.getByTicketNumber(ticketNumber);
        if (ticketDb.isPresent()) {
            return ticketDb.get();
        } else {
            throw new ResourceNotFoundException("Ticket not found with Ticket number : " + ticketDb);
        }
    }
}
