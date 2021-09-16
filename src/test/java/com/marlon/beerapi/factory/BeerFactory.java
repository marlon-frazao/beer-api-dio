package com.marlon.beerapi.factory;

import com.marlon.beerapi.dto.BeerDTO;
import com.marlon.beerapi.entities.Beer;
import com.marlon.beerapi.enums.BeerType;

public class BeerFactory {

	public static Beer createBeer() {
		return new Beer(null, "Kaiser", "Ambev", 50, 10, BeerType.LAGER);
	}
	
	public static Beer createBeer(Long id) {
		Beer beer = createBeer();
		beer.setId(id);
		return beer;
	}
	
	public static BeerDTO createBeerDTO() {
		return createBeer().toDTO();
	}
	
	public static BeerDTO createBeerDTO(Long id) {
		return createBeer(id).toDTO();
	}
}
