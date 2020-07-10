package com.onder.service;

import com.onder.model.Flight;

import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);

    Flight updateFlight(Flight flight);

    List<Flight> getAllFlights();

    Flight getFlightById(Long flightId);

    void deleteFlight(Long id);
}
