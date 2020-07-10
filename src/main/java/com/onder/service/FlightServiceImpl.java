package com.onder.service;

import com.onder.exception.ResourceNotFoundException;
import com.onder.model.Flight;
import com.onder.repository.AirlineRepository;
import com.onder.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {


    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        Optional<Flight> flightDb = this.flightRepository.findById(flight.getId());
        if (flightDb.isPresent()) {
            Flight flightUpdate = flightDb.get();
            flightUpdate.setId(flight.getId());
            flightUpdate.setOriginIataCode(flight.getOriginIataCode());
            flightUpdate.setDestinationIataCode(flight.getDestinationIataCode());
            flightUpdate.setTwoLetterCode(flight.getTwoLetterCode());
            flightRepository.save(flightUpdate);
            return flightUpdate;
        } else {
            throw new ResourceNotFoundException("Airline not found with id: " + flight.getId());
        }

    }

    @Override
    public List<Flight> getAllFlights() {
        return this.flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long flightId) {
        Optional<Flight> flightDb = this.flightRepository.findById(flightId);
        if (flightDb.isPresent()) {
            return flightDb.get();
        } else {
            throw new ResourceNotFoundException("Airline not found with id: " + flightId);
        }
    }

    @Override
    public void deleteFlight(Long id) {
        Optional<Flight> flightDb = this.flightRepository.findById(id);
        if (flightDb.isPresent()) {
            this.flightRepository.delete(flightDb.get());
        } else {
            throw new ResourceNotFoundException("Airline not found with id: " + id);
        }
    }
}
