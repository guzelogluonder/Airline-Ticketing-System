package com.onder.service;

import com.onder.exception.ResourceNotFoundException;
import com.onder.model.Airline;
import com.onder.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Airline updateAirline(Airline airline) {
        Optional<Airline> airlineDb = this.airlineRepository.findById(airline.getId());

        if (airlineDb.isPresent()) {
            Airline airlineUpdate = airlineDb.get();
            airlineUpdate.setId(airline.getId());
            airlineUpdate.setName(airline.getName());
            airlineUpdate.setTwoLetterCode(airline.getTwoLetterCode());
            airlineRepository.save(airlineUpdate);
            return airlineUpdate;
        } else {
            throw new ResourceNotFoundException("Airline not found with id: " + airline.getId());
        }

    }

    @Override
    public List<Airline> getAllAirlines() {
        return this.airlineRepository.findAll();
    }

    @Override
    public Airline getAirlineById(Long airlineId) {
        Optional<Airline> airlineDb = this.airlineRepository.findById(airlineId);
        if (airlineDb.isPresent()) {
            return airlineDb.get();
        } else {
            throw new ResourceNotFoundException("Airline not found with id: " + airlineId);
        }
    }

    @Override
    public void deleteAirline(Long id) {
        Optional<Airline> airlineDb = this.airlineRepository.findById(id);
        if (airlineDb.isPresent()) {
            this.airlineRepository.delete(airlineDb.get());
        } else {
            throw new ResourceNotFoundException("Aitline not found with id: " + id);
        }

    }
}
