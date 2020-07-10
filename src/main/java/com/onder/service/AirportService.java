package com.onder.service;


import com.onder.model.Airport;

import java.util.List;

public interface AirportService {
    Airport createAirport(Airport airport);

    Airport updateAirport(Airport airport);

    List<Airport> getAllAirports();

    Airport getAirportById(Long airportId);

    void deleteAirport(Long id);
}
