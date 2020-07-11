package com.onder.repository;

import com.onder.model.Flight;
import com.onder.model.Route;
import com.onder.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight getByOriginIataCode(String origin);

    Flight getByDestinationIataCode(String destination);

    Flight getTicketPrice(String origin, String destination, String twoLetterCode);
    @Modifying
    @Query("update Flight f set f.ticketPrice = ?1 where f.originIataCode =?2 AND f.destinationIataCode = ?3 AND f.twoLetterCode = ?4")
    int updateTicketPrice(Integer tickerPrice, String originIataCode, String destinationIataCode, String twoLetterCode);
}
