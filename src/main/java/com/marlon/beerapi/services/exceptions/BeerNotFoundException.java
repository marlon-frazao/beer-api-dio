package com.marlon.beerapi.services.exceptions;

public class BeerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BeerNotFoundException(String beerName) {
        super(String.format("Beer with name %s not found in the system.", beerName));
    }

    public BeerNotFoundException(Long id) {
        super(String.format("Beer with id %s not found in the system.", id));
    }
}
