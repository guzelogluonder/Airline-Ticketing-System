package com.onder.service;

import com.onder.exception.ResourceNotFoundException;
import com.onder.model.Route;
import com.onder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        Optional<Route> routeDb = this.routeRepository.findById(route.getId());
        if (routeDb.isPresent()) {
            Route routeUpdate = routeDb.get();
            routeUpdate.setId(route.getId());
            routeUpdate.setOrigin(route.getOrigin());
            routeUpdate.setDestination(route.getDestination());
            routeRepository.save(routeUpdate);
            return routeUpdate;
        } else {
            throw new ResourceNotFoundException("Route not found with id: " + route.getId());
        }
    }


    @Override
    public List<Route> getAllRoute() {
        return this.routeRepository.findAll();
    }

    @Override
    public Route getRouteById(Long routeId) {
        Optional<Route> routeDb = this.routeRepository.findById(routeId);
        if (routeDb.isPresent()) {
            return routeDb.get();
        } else {
            throw new ResourceNotFoundException("Route not found with id: " + routeDb);
        }
    }



    @Override
    public void deleteRoute(Long id) {
        Optional<Route> routeDb = this.routeRepository.findById(id);
        if (routeDb.isPresent()) {
            this.routeRepository.delete(routeDb.get());
        } else {
            throw new ResourceNotFoundException("Route not found with id: " + id);

        }

    }
}
