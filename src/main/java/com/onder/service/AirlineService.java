package com.onder.service;

import com.onder.model.Airline;

import java.util.List;

public interface AirlineService {
    Airline createAirline(Airline airline);

    Airline updateAirline(Airline airline);

    List<Airline> getAllAirlines();

    Airline getAirlineById(Long airlineId);

    void deleteAirline(Long id);

}
