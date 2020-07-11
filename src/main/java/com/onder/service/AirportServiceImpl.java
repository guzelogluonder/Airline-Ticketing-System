package com.onder.service;

import com.onder.exception.ResourceNotFoundException;
import com.onder.model.Airport;
import com.onder.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport airport) {
        Optional<Airport> airportDb = this.airportRepository.findById(airport.getId());
        if (airportDb.isPresent()) {
            Airport airportUpdate = airportDb.get();
            airportUpdate.setId(airport.getId());
            airportUpdate.setName(airport.getName());
            airportUpdate.setIataCode(airport.getIataCode());
            airportRepository.save(airportUpdate);
            return airportUpdate;
        } else {
            throw new ResourceNotFoundException("Airport not found with id: " + airport.getId());
        }
    }

    @Override
    public List<Airport> getAllAirports() {
        return this.airportRepository.findAll();
    }

    @Override
    public Airport getAirportById(Long airportId) {
        Optional<Airport> airportDb = this.airportRepository.findById(airportId);
        if (airportDb.isPresent()) {
            return airportDb.get();
        } else {
            throw new ResourceNotFoundException("Airport not found with id: " + airportId);
        }
    }

    @Override
    public void deleteAirport(Long id) {
        Optional<Airport> airportDb = this.airportRepository.findById(id);
        if (airportDb.isPresent()) {
            this.airportRepository.delete(airportDb.get());
        } else {
            throw new ResourceNotFoundException("Airport not found with id: " + id);

        }
    }
}
