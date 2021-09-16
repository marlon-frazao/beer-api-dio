package com.marlon.beerapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlon.beerapi.entities.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> {

	Optional<Beer> findByName(String name);
}
