package com.onder.repository;

import com.onder.model.Flight;
import com.onder.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight getByOriginIataCode(String origin);
    Flight getByDestinationIataCode(String destination);
}
