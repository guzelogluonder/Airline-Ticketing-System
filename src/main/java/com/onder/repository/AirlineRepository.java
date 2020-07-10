package com.onder.repository;

import com.onder.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository  extends JpaRepository<Airline,Long> {
}
