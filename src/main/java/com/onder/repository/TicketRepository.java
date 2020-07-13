package com.onder.repository;

import com.onder.model.Flight;
import com.onder.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> getTicketsByFlight(String airlineTwoLetterCode, String originIataCode, String destinationIataCode);

    Optional<Ticket> getByTicketNumber(Long ticketNumber);
}
