package com.onder.controller;

import com.onder.model.Airport;
import com.onder.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAllAirports() {
        return ResponseEntity.ok().body(airportService.getAllAirports());
    }

    @GetMapping("/airports/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable long id) {
        return ResponseEntity.ok().body(airportService.getAirportById(id));
    }

    @PostMapping("/airports")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return ResponseEntity.ok().body(this.airportService.createAirport(airport));
    }

    @PutMapping("/airports/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable long id, Airport airport) {
        return ResponseEntity.ok().body(this.airportService.updateAirport(airport));
    }

    @DeleteMapping("/airports/{id}")
    public HttpStatus deleteAirport(@PathVariable long id) {
        this.airportService.deleteAirport(id);
        return HttpStatus.OK;
    }
}
