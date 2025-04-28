package com.onder.service;

import com.onder.exception.ResourceNotFoundException;
import com.onder.model.Airline;
import com.onder.model.Flight;
import com.onder.model.Route;
import com.onder.repository.AirlineRepository;
import com.onder.repository.FlightRepository;
import com.onder.repository.RouteRepository;
import com.onder.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {


    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /*
     * creating flight to route
     * checking airline two letter code and origin iata code and destination code
     * @param flight flight knowledges in plain format
     * @return saving to DB flight knowledges
     * */
    @Override
    public Flight createFlight(Flight flight) {
        if (StringUtils.isEmpty(flight.getTwoLetterCode())) {
            throw new ResourceNotFoundException("Airline not found with twoLetterCode: " + flight.getTwoLetterCode());
        }
        if (StringUtils.isEmpty(flight.getOriginIataCode())) {
            throw new ResourceNotFoundException("Airline not found with origin: " + flight.getOriginIataCode());
        }
        if (StringUtils.isEmpty(flight.getDestinationIataCode())) {
            throw new ResourceNotFoundException("Airline not found with Destination: " + flight.getDestinationIataCode());
        }
        Airline airline = airlineRepository.getByTwoLetterCode(flight.getTwoLetterCode());
        Route route = routeRepository.getByOriginDestination(flight.getOriginIataCode(), flight.getDestinationIataCode());
        if (airline == null) {
            throw new IllegalArgumentException("Invalid airline Code!");
        }
        if (route == null) {
            throw new IllegalArgumentException("Invalid route Code!");
        }
        return flightRepository.save(flight);
    }

    /*
     * updating flight
     * @param flight flight knowledges in plain format
     * @return updating flight
     * */
    @Override
    public Flight updateFlight(Flight flight) {
        Optional<Flight> flightDb = this.flightRepository.findById(flight.getId());
        if (flightDb.isPresent()) {
            Flight flightUpdate = flightDb.get();
            flightUpdate.setId(flight.getId());
            flightUpdate.setOriginIataCode(flight.getOriginIataCode());
            flightUpdate.setDestinationIataCode(flight.getDestinationIataCode());
            flightUpdate.setTwoLetterCode(flight.getTwoLetterCode());
            flightUpdate.setTicketPrice(flight.getTicketPrice());
            flightRepository.save(flightUpdate);
            return flightUpdate;
        } else {
            throw new ResourceNotFoundException("Airline not found with id: " + flight.getId());
        }

    }

    /*
     * getting all flights
     * @return finds all flights
     * */
    @Override
    public List<Flight> getAllFlights() {
        return this.flightRepository.findAll();
    }

    /*
     * getting flights by Id
     * @param flightId flight's Id
     * @return gets flights by Id
     * */
    @Override
    public Flight getFlightById(Long flightId) {
        Optional<Flight> flightDb = this.flightRepository.findById(flightId);
        if (flightDb.isPresent()) {
            return flightDb.get();
        } else {
            throw new ResourceNotFoundException("Airline not found with id: " + flightId);
        }
    }

    /*
     * deleting flight by Id
     * @param id
     * @return deletes flight by id
     * */
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
