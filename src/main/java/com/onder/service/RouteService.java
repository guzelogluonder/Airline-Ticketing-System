package com.onder.service;

import com.onder.model.Airport;
import com.onder.model.Route;

import java.util.List;

public interface RouteService {
    Route createRoute(Route route);

    Route updateRoute(Route route);

    List<Route> getAllRoute();

    Route getRouteById(Long routeId);

    void deleteRoute(Long id);
}