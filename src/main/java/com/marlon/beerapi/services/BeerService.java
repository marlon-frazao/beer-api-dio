package com.marlon.beerapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marlon.beerapi.dto.BeerDTO;
import com.marlon.beerapi.entities.Beer;
import com.marlon.beerapi.repositories.BeerRepository;
import com.marlon.beerapi.services.exceptions.BeerAlreadyRegisteredException;
import com.marlon.beerapi.services.exceptions.BeerNotFoundException;
import com.marlon.beerapi.services.exceptions.BeerStockExceededException;
import com.marlon.beerapi.services.exceptions.BeerStockNegativeException;

@Service
public class BeerService {

	@Autowired
	private BeerRepository repository;

	@Transactional
	public BeerDTO create(BeerDTO dto) {
		verifyIfIsAlreadyRegistered(dto.getName());
		return repository.save(dto.toModel()).toDTO();
	}

	@Transactional(readOnly = true)
	public BeerDTO findByName(String name) {
		return repository.findByName(name)
				.orElseThrow(() -> new BeerNotFoundException(name)).toDTO();
	}
	
	@Transactional(readOnly = true)
	public List<BeerDTO> findAll() {
		return repository.findAll().stream().map(Beer::toDTO).collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteById(Long id) {
		verifyIfExists(id);
		repository.deleteById(id);
	}
	
	@Transactional
	public BeerDTO increment(Long id, int quantity) {
		Beer beer = verifyIfExists(id);
		int incremented = quantity + beer.getQuantity();
		
		if(incremented <= beer.getMax()) {
			beer.setQuantity(incremented);		
			return repository.save(beer).toDTO();
		}
		
		throw new BeerStockExceededException(id, quantity);
	}
	
	@Transactional
	public BeerDTO decrement(Long id, int quantity) {
		Beer beer = verifyIfExists(id);
		int decremented = beer.getQuantity() - quantity;
		
		if(decremented >= 0) {
			beer.setQuantity(decremented);		
			return repository.save(beer).toDTO();
		}
		
		throw new BeerStockNegativeException(id, quantity);
	}
 
	private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
		if (repository.findByName(name).isPresent()) {
			throw new BeerAlreadyRegisteredException(name);
		}
	}

	private Beer verifyIfExists(Long id) throws BeerNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new BeerNotFoundException(id));
	}
}
