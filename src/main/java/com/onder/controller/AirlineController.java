package com.onder.controller;

import com.onder.model.Airline;
import com.onder.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirlineController {


    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    //gets all airlines
    @GetMapping("/airlines")
    public ResponseEntity<List<Airline>> getAllAirlines() {
        return ResponseEntity.ok().body(airlineService.getAllAirlines());
    }

    // gets airline by id
    @GetMapping("/airlines/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable long id) {
        return ResponseEntity.ok().body(airlineService.getAirlineById(id));
    }

    // creates airline
    @PostMapping("/airlines")
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
        return ResponseEntity.ok().body(this.airlineService.createAirline(airline));
    }

    //updates airline
    @PutMapping("/airlines/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable long id, @RequestBody Airline airline) {
        airline.setId(id);
        return ResponseEntity.ok().body(this.airlineService.updateAirline(airline));
    }

    //deletes airline by id
    @DeleteMapping("/airlines/{id}")
    public HttpStatus deleteAirline(@PathVariable long id) {
        this.airlineService.deleteAirline(id);
        return HttpStatus.OK;
    }

    public AirlineService getAirlineService() {
        return airlineService;
    }
}