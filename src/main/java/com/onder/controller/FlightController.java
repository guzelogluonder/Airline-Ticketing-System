package com.onder.controller;

import com.onder.model.Airline;
import com.onder.model.Flight;
import com.onder.model.Route;
import com.onder.service.AirlineService;
import com.onder.service.FlightService;
import com.onder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok().body(flightService.getAllFlights());
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id) {
        return ResponseEntity.ok().body(flightService.getFlightById(id));
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {

        return ResponseEntity.ok().body(this.flightService.createFlight(flight));
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long id, Flight flight) {
        return ResponseEntity.ok().body(this.flightService.updateFlight(flight));
    }

    @DeleteMapping("/flights/{id}")
    public HttpStatus deleteFlight(@PathVariable long id) {
        this.flightService.deleteFlight(id);
        return HttpStatus.OK;
    }

}
