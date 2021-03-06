package com.onder.controller;

import com.onder.model.Route;
import com.onder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    //gets all routes
    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getAllRoute() {
        return ResponseEntity.ok().body(routeService.getAllRoute());
    }

    //gets route by id
    @GetMapping("/routes/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable long id) {
        return ResponseEntity.ok().body(routeService.getRouteById(id));
    }

    //creates route
    @PostMapping("/routes")
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        return ResponseEntity.ok().body(this.routeService.createRoute(route));
    }

    //updates route by id
    @PutMapping("/routes/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable long id, Route route) {
        return ResponseEntity.ok().body(this.routeService.updateRoute(route));
    }

    //deletes route by id
    @DeleteMapping("/routes/{id}")
    public HttpStatus deleteRoute(@PathVariable long id) {
        this.routeService.deleteRoute(id);
        return HttpStatus.OK;
    }
}
