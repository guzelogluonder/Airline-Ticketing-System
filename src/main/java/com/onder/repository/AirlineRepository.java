package com.onder.repository;

import com.onder.model.Airline;
import com.onder.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Airline getByTwoLetterCode(String twoLetterCode);

}
